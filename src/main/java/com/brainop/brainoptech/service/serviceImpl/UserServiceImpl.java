package com.brainop.brainoptech.service.serviceImpl;

import com.brainop.brainoptech.entity.User;
import com.brainop.brainoptech.exception.ResourceNotFoundException;
import com.brainop.brainoptech.payload.UserDto;
import com.brainop.brainoptech.repo.UsersRepo;
import com.brainop.brainoptech.service.FileService;
import com.brainop.brainoptech.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private FileService fileService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto, User.class);

        User createdUser = this.usersRepo.save(user);

        return this.modelMapper.map(createdUser, UserDto.class);
    }

    @Override
    public UserDto uploadUserImage(Integer id, String path, MultipartFile key) throws IOException {

        User fetchedUser = this.usersRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "user_id", id));

        String userImage = this.fileService.uploadImage(path, key);
        fetchedUser.setImage(userImage);
        this.usersRepo.save(fetchedUser);

        return this.modelMapper.map(fetchedUser, UserDto.class);
    }
}
