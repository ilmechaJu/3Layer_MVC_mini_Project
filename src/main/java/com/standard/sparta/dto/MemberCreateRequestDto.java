package com.standard.sparta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 회원 생성 요청을 위한 DTO.
 */
@Getter
@AllArgsConstructor
public class MemberCreateRequestDto {
    private String email;
    private String name;
}
