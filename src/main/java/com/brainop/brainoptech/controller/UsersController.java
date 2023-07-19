package com.brainop.brainoptech.controller;

import com.brainop.brainoptech.payload.ApiResponse;
import com.brainop.brainoptech.payload.UserDto;
import com.brainop.brainoptech.service.FileService;
import com.brainop.brainoptech.service.PersonService;
import com.brainop.brainoptech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Value("${project.userImages}")
    private String path;

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

        UserDto createdUser = this.userService.createUser(userDto);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/imageUpload/{userId}")
    public ResponseEntity<ApiResponse> uploadUserImage(@PathVariable("userId") Integer userid, @RequestParam("image") MultipartFile image) throws IOException {

        this.userService.uploadUserImage(userid, path,image);

        return new ResponseEntity<>(new ApiResponse("image uploaded", true), HttpStatus.OK);

    }

}
