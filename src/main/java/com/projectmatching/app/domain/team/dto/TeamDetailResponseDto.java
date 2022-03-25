package com.projectmatching.app.domain.team.dto;

import com.projectmatching.app.domain.comment.dto.TeamCommentDto;
import com.projectmatching.app.domain.team.entity.Team;
import com.projectmatching.app.domain.team.entity.TeamTech;
import com.projectmatching.app.domain.techStack.entity.TechStack;
import com.projectmatching.app.domain.user.entity.UserTeam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class TeamDetailResponseDto {

    private Long user_id;
    private Long team_id;
    private String name;
    private String content;
    private String session;
    private List<String> tech_stack;
    private String img;
    private Long read;
    private Boolean status;
    private int comment_cnt;
    private int like_cnt;
    private LocalDateTime create_at;
    private LocalDateTime update_at;

    private List<TeamCommentDto> comment;

    public static TeamDetailResponseDto createEmpty(){return new TeamDetailResponseDto();}


    //entity를 dto로
    public static TeamDetailResponseDto of(Team team){
        TeamDetailResponseDto teamResponseDto = createEmpty();
        BeanUtils.copyProperties(team, teamResponseDto);

        teamResponseDto.comment_cnt = team.getTeamComments().size();
        teamResponseDto.like_cnt = team.getTeamLikings().size();

        teamResponseDto.status = team.getStatus()=="NA" ? Boolean.FALSE : Boolean.TRUE;
        teamResponseDto.create_at = team.getCreatedAt();
        teamResponseDto.update_at = team.getUpdatedAt();

        return teamResponseDto;
    }

    //dto를 entity로
    public Team asEntity(){
        Team team = new Team();
        BeanUtils.copyProperties(this,team);
        return team;
    }
}
