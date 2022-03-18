package com.projectmatching.app.domain.team.dto;

import com.projectmatching.app.domain.comment.entity.TeamComment;
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

        List<TeamComment> teamCommentList = team.getTeamComments().stream().collect(Collectors.toList());
        List<TeamCommentDto> findComment = new ArrayList<>();
        for (TeamComment c : teamCommentList){
            findComment.add(new TeamCommentDto(c.getId(), c.getParentId(), c.getSecret(), c.getContent(), c.getCreatedAt()));
        }
        teamResponseDto.comment = findComment;

        teamResponseDto.comment_cnt = team.getTeamComments().size();
        //teamResponseDto.like_cnt = team.getUserLikings().size();

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
