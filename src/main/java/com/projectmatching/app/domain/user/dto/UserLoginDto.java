package com.projectmatching.app.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter @Setter
@NoArgsConstructor
public class UserLoginDto {

    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,20}") //8자 이상 20자 이하, 숫자 한개이상 특수문자 한개이상 포함 공백 불가
    private  String pwd;

}
