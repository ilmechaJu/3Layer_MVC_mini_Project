package com.standard.sparta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberUpdateRequestDto {

    private Long id;
    private String email;
    private String name;
    private boolean isDeleted;


}
