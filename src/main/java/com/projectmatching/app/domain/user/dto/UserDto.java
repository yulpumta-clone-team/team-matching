package com.projectmatching.app.domain.user.dto;

import com.projectmatching.app.domain.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private Role role;
    private String email;
    private String name;
    private String pwd;
    private String portfolio;
    private String slogan;
    private String img;
    private String content;//자기소개

    @Builder
    public UserDto(String email,String name){
        this.email = email;
        this.name = name;
    }



}
