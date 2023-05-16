package com.user.service.api.controllers;

import com.user.service.api.dto.UserDto;
import com.user.service.api.service.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@Controller("/api/v1/users")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Get(uri = "/getAllUsers", produces = MediaType.APPLICATION_JSON)
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = userService.getAllUsers();
        LOGGER.info("@@@ getAllUsers userList -> {} ",userDtoList);
        return userDtoList;
    }

    @Post(uri = "/createUser", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public UserDto createUser(@Body UserDto userDto) {
        UserDto createUserDto = userService.createUser(userDto);
        LOGGER.info("@@@ createUser createUser -> {} ", createUserDto);
        return createUserDto;
    }

    @Get(uri = "/getUser/{userId}", produces = MediaType.APPLICATION_JSON)
    public UserDto getUser(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUserById(userId);
        LOGGER.info("@@@ getUsers user -> {}",userDto);
        return userDto;
    }

    @Put(uri = "/updateUser/{userId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public UserDto updateUser(@PathVariable("userId") String userId,@Body UserDto userDto) {
        UserDto updateUserDto = userService.updateUser(userId,userDto);
        LOGGER.info("@@@ updateUser updateUser -> {} ", updateUserDto);
        return updateUserDto;
    }

    @Delete(uri = "/deleteUser/{userId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<?> deleteUser(@PathVariable("userId") String userId) {
        LOGGER.info("@@@ deleteUser ::: ");
        userService.deleteByUserId(userId);
        return HttpResponse.status(HttpStatus.OK).status(200).body(Map.of("success",true,"status","Success","message","user has been deleted"));
    }



}