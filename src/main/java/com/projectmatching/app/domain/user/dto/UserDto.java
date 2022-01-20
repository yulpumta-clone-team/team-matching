package com.projectmatching.app.domain.user.dto;

import com.projectmatching.app.domain.user.Role;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
public class UserDto {
    private Long id;
    private String oauthId;
    private Role role;
    private String email;
    private String name;
    private String pwd;


}
