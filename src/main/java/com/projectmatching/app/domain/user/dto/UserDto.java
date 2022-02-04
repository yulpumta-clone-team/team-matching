package com.projectmatching.app.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) //null 이면 생성되지 않음
public class UserDto {

    @Email
    private String email;

    private String name;

    @NotEmpty
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,20}") //8자 이상 20자 이하, 숫자 한개이상 특수문자 한개이상 포함 공백 불가
    private String pwd;

    private String portfolio;
    private String slogan;
    private String img;
    private String content;//자기소개
    
    private String hope_session; //원하는 작업기간

    private String job; //직업






}
