package com.brainop.brainoptech.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private PersonDto person;

    private String image;

    private String query;
}
