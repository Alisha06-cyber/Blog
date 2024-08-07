package com.test.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.test.entities.User;
import com.test.exceptions.ResourceNotFoundException;
import com.test.payloads.UserDto;
import com.test.repositories.UserRepo;
import com.test.services.UserService;
@Service
public class UserServiceImpl implements UserService {
@Autowired
	private UserRepo userRepo;
   @Autowired
	private ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userDto) {
	User user= this.dtoTOUser(userDto);
	User saveUser=this.userRepo.save(user);
	return this.userToDto(saveUser);
	
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
	user.setName(userDto.getName());
	user.setEmail(userDto.getEmail());
	user.setPassword(userDto.getPassword());
	user.setAbout(userDto.getAbout());
	User updateuser=this.userRepo.save(user);	
			UserDto userDto1=this.userToDto(updateuser);
		return userDto1 ;
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> GetAllUsers() {
		List<User> users= this.userRepo.findAll();
		List<UserDto> userdtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
	
	return userdtos;
			}

	@Override
	public void deleteUser(Integer uid) {
		User user=this.userRepo.findById(uid).orElseThrow(()->new ResourceNotFoundException("User","Id",uid));
		this.userRepo.delete(user);
	}
	//manuully convert krt ahe convert krt ahe
	//we use modelMapper libary
public User dtoTOUser(UserDto userDto)//eka object la user object madhy convert krt ahe
{
	//automatic covert using modelMapper
	User user=this.modelMapper.map(userDto, User.class);
/*mannally convert
	User user=new User();
user.setId(userDto.getId());
user.setName(userDto.getName());
user.setEmail(userDto.getEmail());
user.setPassword(userDto.getPassword());
user.setAbout(userDto.getAbout());*/
return user;
}
public UserDto userToDto(User user)
{
	UserDto userdto=this.modelMapper.map(user, UserDto.class);
	/*UserDto userDto=new UserDto();
	userDto.setId(user.getId());
	userDto.setName(user.getName());
	userDto.setEmail(user.getEmail());
	userDto.setPassword(user.getPassword());
	userDto.setAbout(user.getAbout());*/
	return userdto;
}

}
