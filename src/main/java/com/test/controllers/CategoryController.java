package com.test.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.payloads.ApiResponse;
import com.test.payloads.CategoryDto;
import com.test.payloads.UserDto;
import com.test.services.CategoryService;

@RestController

@RequestMapping("api/categories")
public class CategoryController {
//hi//
//hello//
//---//
//*//
	@Autowired
	private CategoryService categoryService;
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
	{

		CategoryDto createcategory =this.categoryService.createCategory(categoryDto);
	return new ResponseEntity<CategoryDto>(createcategory,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable  Integer catId)
	{

		CategoryDto updatedcategory =this.categoryService.updateCategory(categoryDto,catId);
	return new ResponseEntity<CategoryDto>(updatedcategory,HttpStatus.OK);
	}
	
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable  Integer catId)
	{
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category delete sucessfully",true) ,HttpStatus.OK);

	
	}
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getcategory(@PathVariable Integer catId)
	{
		CategoryDto categoryDto=this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	//getall
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getcategories()
	{
		List<CategoryDto> categories=this.categoryService.getcategories();
	return ResponseEntity.ok(categories);
	}
	
}
	
	
	