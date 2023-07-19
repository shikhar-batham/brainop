package com.brainop.brainoptech.service.serviceImpl;

import com.brainop.brainoptech.entity.Person;
import com.brainop.brainoptech.exception.ResourceNotFoundException;
import com.brainop.brainoptech.payload.PersonDto;
import com.brainop.brainoptech.repo.PersonRepo;
import com.brainop.brainoptech.service.FileService;
import com.brainop.brainoptech.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private FileService fileService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonDto createPerson(PersonDto personDto) {

        Person person = this.modelMapper.map(personDto, Person.class);

        Person createdPerson = this.personRepo.save(person);


        return this.modelMapper.map(createdPerson, PersonDto.class);
    }

    @Override
    public PersonDto getPersonById(Integer personId) {
        return null;
    }

    @Override
    public PersonDto uploadPersonImage(Integer id, String path, MultipartFile file) throws IOException {

        Person person = this.personRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person", "person_id", id));

        String personImage = this.fileService.uploadImage(path, file);

        return null;
    }
}
