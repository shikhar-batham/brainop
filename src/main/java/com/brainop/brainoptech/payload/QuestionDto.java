package com.brainop.brainoptech.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto {

    private Integer quesId;
    private String value;
    private UserDto user;
}
