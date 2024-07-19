package com.test.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.description.annotation.AnnotationValue.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.test.entities.Category;
import com.test.entities.Post;
import com.test.entities.User;
import com.test.exceptions.ResourceNotFoundException;
import com.test.payloads.PostDto;
import com.test.payloads.PostResponse;
import com.test.repositories.CategoryRepo;
import com.test.repositories.PostRepo;
import com.test.repositories.UserRepo;
import com.test.services.PostService;
@Service
public class PostServiceImpl implements PostService {
	@Autowired
 private PostRepo postRepo;
	@Autowired
	
	private ModelMapper modelMapper;

	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," User id", userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category"," Category id", categoryId));
		
		Post post=this.modelMapper.map(postDto,Post.class);
		post.setImageName("default.png");
	post.setAddedDate(new Date());
	post.setUser(user);
	post.setCategory(category);
	Post newpost=this.postRepo.save(post);
	return this.modelMapper.map(newpost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto,Integer postId) {
		Post	post =this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId) );
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(post.getImageName());
		Post updatepost=this.postRepo.save(post);
		return this.modelMapper.map(updatepost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post =this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId) );
		this.postRepo.delete(post);
	}

	
	
	@Override
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy) {
		//CREATE PAGINATION
		
org.springframework.data.domain.Pageable p=PageRequest.of(pageNumber, pageSize,org.springframework.data.domain.Sort.by(sortBy));
			Page<Post> pagepost=this.postRepo.findAll(p);
			List<Post> allpost=pagepost.getContent();

		List<PostDto> postDtos= allpost.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagepost.getNumber());
		postResponse.setPageSize(pagepost.getSize());
		postResponse.setTotalElement(pagepost.getTotalElements());
		postResponse.setTotalPages(pagepost.getTotalPages());
		postResponse.setLastpage(pagepost.isLast());
		return postResponse;
	}

	
	@Override
	public PostDto getPostById( Integer postId) {
	Post	post =this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId) );
		PostDto postDto=this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
		List<Post> posts=this.postRepo.findByCategory(cat);
		List<PostDto> postDto=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getPostsByuser(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts=this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postsDtos=posts.stream().map((post)->this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		return postsDtos;
		
		
	}

	
}
