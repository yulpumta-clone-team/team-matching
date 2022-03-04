package com.projectmatching.app.domain.team.dto;

import com.projectmatching.app.domain.team.entity.Team;
import com.projectmatching.app.domain.team.entity.TeamTech;
import com.projectmatching.app.domain.techStack.entity.TechStack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class TeamResponseDto {
    private Long user_id;
    private Long team_id;
    private String name;
    private String session;
    private String img;
    private Long read;
    private int comment_cnt;
    private int like_cnt;
    private List<String> tech_stack;

    public static TeamResponseDto createEmpty(){return new TeamResponseDto();}

    //entity를 dto로
    public static TeamResponseDto of(Team team){
        TeamResponseDto teamResponseDto = createEmpty();
        BeanUtils.copyProperties(team, teamResponseDto);

        teamResponseDto.comment_cnt = team.getTeamComments().size();
        //teamResponseDto.like_cnt = team.getUserLikings().size();

        return teamResponseDto;
    }

    //dto를 entity로
    public Team asEntity(){
        Team team = new Team();
        BeanUtils.copyProperties(this,team);
        return team;

    }
}
