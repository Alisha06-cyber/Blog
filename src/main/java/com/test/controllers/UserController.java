package com.test.controllers;

import java.util.List;
import java.util.Map;

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
import com.test.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService ;
	
	//post -create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUserDto =this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	//put -update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto ,@PathVariable("userId") Integer userId)
	{
		UserDto updateuser=this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updateuser);
		
	}
	//Delete -delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deletesUser(@PathVariable("userId") Integer uid )
	
	{
		this.userService.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user delete sucessfully",true) ,HttpStatus.OK);
	}
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getcategory(@PathVariable Integer UserId)
	{
	UserDto userDto=this.userService.getUserById(UserId);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	//getall
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getuser()
	{
		List<UserDto> users=this.userService.GetAllUsers();
	return ResponseEntity.ok(users);
	}

	
	
		
		
	
}
