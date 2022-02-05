package com.projectmatching.app.domain.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
public class UserProfileDto {

    private final String name;
    private final String slogan;
    private final String description;
    private final String img;
    private final String hope_session;
    private final List<String> skills;
    private final String job;
    private final String status;


}
