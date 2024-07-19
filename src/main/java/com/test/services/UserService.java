package com.test.services;

import java.util.List;

import com.test.payloads.UserDto;

public interface UserService {
UserDto createUser(UserDto user);
UserDto updateUser(UserDto userDto,Integer userId);
UserDto getUserById(Integer userId);
List<UserDto> GetAllUsers();
void deleteUser(Integer userId);
}
