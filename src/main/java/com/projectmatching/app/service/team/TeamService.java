package com.projectmatching.app.service.team;

import com.projectmatching.app.config.resTemplate.ResponeException;
import com.projectmatching.app.domain.team.dto.TeamRequestDto;
import com.projectmatching.app.domain.team.dto.TeamResponseDto;
import com.projectmatching.app.domain.team.entity.Team;
import com.projectmatching.app.domain.team.entity.TeamTech;
import com.projectmatching.app.domain.team.repository.TeamRepository;
import com.projectmatching.app.domain.team.repository.TeamTechRepository;
import com.projectmatching.app.domain.techStack.TechStackRepository;
import com.projectmatching.app.domain.techStack.entity.TechStack;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.projectmatching.app.constant.ResponseTemplateStatus.GET_TEAMS_ERROR;
import static com.projectmatching.app.constant.ResponseTemplateStatus.SAVE_TEAM_ERROR;


@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final TechStackRepository techStackRepository;
    private final TeamTechRepository teamTechRepository;

    public Long save(TeamRequestDto requestDto) throws ResponeException {
        try {
            Team team = Team.builder()
                    .name(requestDto.getName())
                    .session(requestDto.getSession())
                    .img(requestDto.getImg())
                    .content(requestDto.getContent())
                    .build();

            Long teamId = teamRepository.save(team).getTeam_id();

            List<String> techs = requestDto.getTech_stack();
            for (String t : techs){
                TechStack techStack = techStackRepository.findByName(t).orElseThrow(() -> new ResponeException(SAVE_TEAM_ERROR));
                TeamTech teamTech = TeamTech.builder()
                        .team(team)
                        .techStack(techStack)
                        .build();

                teamTechRepository.save(teamTech);
            }

            return teamId;

        }catch (Exception e){
            throw new ResponeException(SAVE_TEAM_ERROR);
        }
    }

    public Page<TeamResponseDto> getTeams(Pageable pageable) throws ResponeException {
        try{
            return teamRepository.getTeams(pageable);
        }catch (Exception e){
            throw new ResponeException(GET_TEAMS_ERROR);
        }
    }
}
