package com.brainop.brainoptech.service.serviceImpl;

import com.brainop.brainoptech.entity.ImageUpload;
import com.brainop.brainoptech.repo.ImageUploadRepo;
import com.brainop.brainoptech.service.FileService;
import com.brainop.brainoptech.util.ImageUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private ImageUploadRepo imageUploadRepo;

    private static final List<String> ALLOWED_IMAGE_FORMATS = Arrays.asList("png", "jpeg", "jpg");

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        assert name != null;

        String fileExtension = getFileExtension(name);

        if (!isAllowedImageFormat(fileExtension)) {
            return null;
        }

        if (file.getSize() > 2 * 1024) {

            File compressedImageFile = compressImage(file, fileExtension);
            FileInputStream input = new FileInputStream(compressedImageFile);
            file = new MockMultipartFile("file", file.getName(), "text/plain", new BufferedInputStream(input));
        }

        String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));
        String filePath = path + File.separator + fileName;

        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName;
    }

    private String getFileExtension(String fileName) {

        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }

        return "";
    }

    private boolean isAllowedImageFormat(String fileExtension) {

        return ALLOWED_IMAGE_FORMATS.contains(fileExtension);
    }

    private File compressImage(MultipartFile file, String fileExtension) throws IOException {

        File compressedImageFile = File.createTempFile("comp", file.getOriginalFilename());

        Thumbnails.of(file.getInputStream())
                .size(800, 600) // Set the desired dimensions for the compressed image
                .outputFormat(fileExtension) // Set the desired output format
                .outputQuality(0.8) // Set the desired output quality (0.0 - 1.0)
                .toFile(compressedImageFile);

        return compressedImageFile;
    }

//    @Override
//    public String uploadImageFile(MultipartFile file) throws IOException {
//
//        ImageUpload imageData = imageUploadRepo.save(ImageUpload.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .imageData(ImageUtils.compressImage(file.getBytes()))
//                .build());
//
//        if (imageData != null)
//            return "file uploaded successfully!" + file.getOriginalFilename();
//        return null;
//    }


}
