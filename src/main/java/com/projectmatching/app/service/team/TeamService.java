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
import com.projectmatching.app.domain.user.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.projectmatching.app.constant.ResponseTemplateStatus.*;


@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final TechStackRepository techStackRepository;
    private final TeamTechRepository teamTechRepository;

    public Long save(TeamRequestDto requestDto) throws ResponeException {
        try {
            Team team = Team.builder()
                    .name(requestDto.getT_name())
                    .session(requestDto.getT_session())
                    .img(requestDto.getT_img())
                    .content(requestDto.getT_content())
                    .read(0L)
                    .build();

            Long teamId = teamRepository.save(team).getTeam_id();

            List<String> techs = requestDto.getT_techs();
            if(techs.size() == 0 ){
                TeamTech teamTech = TeamTech.builder()
                        .team(team)
                        .build();
                teamTechRepository.save(teamTech);
            }

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

    public List<TeamResponseDto> getTeams(PageRequest pageRequest) throws ResponeException {

        return teamRepository.getTeams(pageRequest)
                .stream().map(TeamResponseDto::of)
                .collect(Collectors.toList());
    }


    public void delete(Long teamIdx) throws ResponeException {

    }
}
