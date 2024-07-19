package com.test.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entities.Category;
import com.test.exceptions.ResourceNotFoundException;
import com.test.payloads.CategoryDto;
import com.test.payloads.UserDto;
import com.test.repositories.CategoryRepo;
import com.test.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
@Autowired
private CategoryRepo categoryRepo;
@Autowired
private ModelMapper modelMapper;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat=this.modelMapper.map(categoryDto, Category.class);//categorydto la category madhy convet krte
		Category addedcat=this.categoryRepo.save(cat);
		return this.modelMapper.map(addedcat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category upadtecat=this.categoryRepo.save(cat) ;
		return this.modelMapper.map(upadtecat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getcategories() {
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto>cateDtos=categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class )).collect(Collectors.toList());
		return cateDtos;
	}

}
