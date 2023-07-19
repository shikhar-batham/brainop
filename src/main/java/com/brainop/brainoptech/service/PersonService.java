package com.brainop.brainoptech.service;

import com.brainop.brainoptech.payload.PersonDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PersonService {

    PersonDto createPerson(PersonDto personDto);

    PersonDto getPersonById(Integer personId);

    PersonDto uploadPersonImage(Integer id, String path, MultipartFile file) throws IOException;


}
