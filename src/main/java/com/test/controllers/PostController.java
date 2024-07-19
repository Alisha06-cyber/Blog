package com.test.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.config.AppContants;
import com.test.payloads.ApiResponse;
import com.test.payloads.PostDto;
import com.test.payloads.PostResponse;
import com.test.services.FileService;
import com.test.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	PostService postService;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId)
	{
		PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	//get by user
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId)
	{
		List<PostDto> posts=this.postService.getPostsByuser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	//get by category

		@GetMapping("category/{categoryId}/posts")
		public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId)
		{
			List<PostDto> posts=this.postService.getPostsByuser(categoryId);
			return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
			
		}
		// get all posts
		@GetMapping("/posts")
		public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value="pageNumber",defaultValue = AppContants.PAGE_NUMBER,required = false) Integer pageNumber,
		@RequestParam(value = "pageSize",defaultValue = AppContants.PAGE_SIZE,required = false)Integer pageSize,
		@RequestParam(value = "sortBy",defaultValue = AppContants.SORT_BY,required = false)String sortBy)
				
		{
			PostResponse postResponse=this.postService.getAllPosts(pageNumber,pageSize,sortBy);
			return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
			
		}
		//get posts by id
		@GetMapping("/post/{postId}")
		public ResponseEntity<PostDto> getAllPosts(@PathVariable Integer postId)
		{
			PostDto post=this.postService.getPostById(postId);
			return new ResponseEntity<PostDto>(post,HttpStatus.OK);
			
		}
		//delete post by id
		@DeleteMapping("/post/{postId}")
		public ApiResponse deletePost(@PathVariable Integer postId)
		{
		this.postService.deletePost(postId);
			
			return new ApiResponse("delete sucessfully post",true);
		}
		//update post
		@PutMapping("/post/{postId}")
		public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
		{
		PostDto updatepost=this.postService.updatePost(postDto, postId);
			
			return new ResponseEntity<PostDto>(updatepost,HttpStatus.OK);
		}
		//seraching
		@GetMapping("/posts/search/{keywords}")
		public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
			List<PostDto> result=this.postService.searchPosts(keywords);
			return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
			
		}
		@PostMapping("/post/image/upload/{postId}")
		public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable Integer postId) throws IOException{
			
			PostDto postDto=this.postService.getPostById(postId);
			String fileName=this.fileService.uploadImage(path, image);
			
			postDto.setImageName(fileName);
			PostDto updatepost=this.postService.updatePost(postDto, postId);
			return new ResponseEntity<PostDto>(updatepost,HttpStatus.OK);
			
		}
}

