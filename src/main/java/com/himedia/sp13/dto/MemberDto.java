package com.himedia.sp13.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberDto {
    @NotEmpty(message="아이디 입력")
    @NotNull(message="아이디 입력")
    private String userid;
    @NotEmpty(message="비밀번호 입력")
    @NotNull(message="비밀번호 입력")
    private String pwd;
    @NotEmpty(message="이름 입력")
    @NotNull(message="이름 입력")
    private String name;
    @NotEmpty(message="이메일 입력")
    @NotNull(message="이메일 입력")
    private String email;
    @NotEmpty(message="전화번호 입력")
    @NotNull(message="전화벊 입력")
    private String phone;

    private String provider;

}
