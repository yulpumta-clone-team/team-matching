package com.projectmatching.app.domain.team.dto;

import com.projectmatching.app.domain.team.entity.TeamTech;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class TeamResponseDto {
    private String name;
    private String session;
    private String img;
    private Long read;
    private List<TeamTech> techStackId;

}
