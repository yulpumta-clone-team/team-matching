package com.projectmatching.app.domain.team.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class TeamRequestDto {
    private String name;
    private String session;
    private String img;
    private String content;
    private List<String> tech_stack;
}