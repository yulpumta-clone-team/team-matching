package com.projectmatching.app.domain.team.repository;

import com.projectmatching.app.domain.team.dto.TeamResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamRepositoryCustom {
    Page<TeamResponseDto> getTeams(Pageable pageable);
}
