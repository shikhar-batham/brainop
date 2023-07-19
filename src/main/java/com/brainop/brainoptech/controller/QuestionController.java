package com.brainop.brainoptech.controller;

import com.brainop.brainoptech.payload.ApiResponse;
import com.brainop.brainoptech.payload.QuestionDto;
import com.brainop.brainoptech.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse> postQuestion(@RequestBody QuestionDto questionDto,@PathVariable("userId") Integer userId) {

        QuestionDto questionDtoReturned = this.questionService.postQuestion(questionDto,userId);

        if (questionDtoReturned == null)
            return new ResponseEntity<>(new ApiResponse("No such option", false), HttpStatus.CONFLICT);

        return new ResponseEntity<>(new ApiResponse("yor choice was saved!", true), HttpStatus.OK);
    }

}
