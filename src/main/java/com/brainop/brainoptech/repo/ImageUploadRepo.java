package com.brainop.brainoptech.repo;

import com.brainop.brainoptech.entity.ImageUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.awt.*;
import java.util.Optional;

@Repository
public interface ImageUploadRepo extends JpaRepository<ImageUpload,Integer> {

    Optional<ImageUpload> findByName(String fileName);
}
