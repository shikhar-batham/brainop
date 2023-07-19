package com.brainop.brainoptech.service.serviceImpl;

import com.brainop.brainoptech.entity.Question;
import com.brainop.brainoptech.entity.User;
import com.brainop.brainoptech.exception.ResourceNotFoundException;
import com.brainop.brainoptech.payload.QuestionDto;
import com.brainop.brainoptech.repo.QuestionRepo;
import com.brainop.brainoptech.repo.UsersRepo;
import com.brainop.brainoptech.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public QuestionDto postQuestion(QuestionDto questionDto, Integer userId) {

        User fetchedUser = this.usersRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));

        String val = null;

        String[] choice = {"item1", "item2", "item3", "item4", "item5"};

        String value = questionDto.getValue();

        for (int i = 0; i < choice.length; i++) {
            if (Objects.equals(value, choice[i])) {

                val = value;
                break;
            }
        }

        if (val == null) {
            return null;
        }

        Question question = this.modelMapper.map(questionDto, Question.class);
        question.setValue(val);
        this.questionRepo.save(question);

        return this.modelMapper.map(question, QuestionDto.class);
    }
}
