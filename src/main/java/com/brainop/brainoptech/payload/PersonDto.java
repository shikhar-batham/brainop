package com.brainop.brainoptech.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {

    private Integer personId;
    private String name;
    private String email;
}
