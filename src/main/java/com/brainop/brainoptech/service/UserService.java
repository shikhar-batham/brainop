package com.brainop.brainoptech.service;

import com.brainop.brainoptech.payload.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto uploadUserImage(Integer id,String path, MultipartFile file) throws IOException;

}
