package com.user.service.api.service;

import com.user.service.api.dto.UserDto;

import java.util.List;

public interface UserService {
    public UserDto createUser(UserDto userDto);
    public List<UserDto> getAllUsers();
    public UserDto updateUser(String userId,UserDto userDto);
    public UserDto getUserById(String userId);
    public void deleteByUserId(String userId);
}
