package com.test.services;

import java.util.List;

import com.test.entities.Post;
import com.test.payloads.PostDto;
import com.test.payloads.PostResponse;

public interface PostService {
	//create
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//deletepost
	void deletePost(Integer postId);
	
	//get all posts
PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy);
	
	//get single post
	PostDto getPostById(Integer postId);
	
	//get all post by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all posts by user
	List<PostDto> getPostsByuser(Integer userId);
	
	//search posts
	List<PostDto> searchPosts(String keyword);
	
}
