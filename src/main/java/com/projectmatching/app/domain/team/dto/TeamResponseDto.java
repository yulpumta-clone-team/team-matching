package com.projectmatching.app.domain.team.dto;

import com.projectmatching.app.domain.team.entity.Team;
import com.projectmatching.app.domain.team.entity.TeamTech;
import com.projectmatching.app.domain.techStack.entity.TechStack;
import com.projectmatching.app.domain.user.entity.UserTeam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
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
    private Boolean status;

    public static TeamResponseDto createEmpty(){return new TeamResponseDto();}

    //entity를 dto로
    public static TeamResponseDto of(Team team){
        TeamResponseDto teamResponseDto = createEmpty();
        BeanUtils.copyProperties(team, teamResponseDto);

        List<UserTeam> userTeamList = team.getUserTeams().stream().collect(Collectors.toList());
        if(userTeamList.size() != 0) {
            UserTeam findUser = userTeamList.get(0);
            teamResponseDto.user_id = findUser.getUser().getId();
        }

        Set<TeamTech> teamTechSet = team.getTeamTeches();
        List<String> findTeamTech = new ArrayList<>();
        for (TeamTech tech : teamTechSet){
            TechStack t = tech.getTechStack();
            if(t!=null) findTeamTech.add(t.getName());
        }
        teamResponseDto.tech_stack = findTeamTech;

        teamResponseDto.comment_cnt = team.getTeamComments().size();
        //teamResponseDto.like_cnt = team.getUserLikings().size();

        teamResponseDto.status = team.getStatus()=="NA" ? Boolean.FALSE : Boolean.TRUE;

        return teamResponseDto;
    }

    //dto를 entity로
    public Team asEntity(){
        Team team = new Team();
        BeanUtils.copyProperties(this,team);
        return team;

    }
}
