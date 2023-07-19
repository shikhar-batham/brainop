package com.brainop.brainoptech.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadImage(String path, MultipartFile multipartFile) throws IOException;

//    String uploadImageFile(MultipartFile file) throws IOException;

//    byte[] downloadImage(String fileName);
}
