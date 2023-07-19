package com.brainop.brainoptech.service;

import com.brainop.brainoptech.payload.QuestionDto;

public interface QuestionService {

    QuestionDto postQuestion(QuestionDto questionDto,Integer userId);

}
