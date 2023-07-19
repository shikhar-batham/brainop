package com.brainop.brainoptech.controller;

import com.brainop.brainoptech.payload.PersonDto;
import com.brainop.brainoptech.service.FileService;
import com.brainop.brainoptech.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private FileService fileService;

    @PostMapping("/createPerson")
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto) {

        PersonDto createdPerson = this.personService.createPerson(personDto);

        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }
}
