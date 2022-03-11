package com.projectmatching.app.domain.team.dto;

import com.projectmatching.app.domain.comment.entity.TeamComment;
import com.projectmatching.app.domain.team.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
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
    private String img;
    private Long read;
    private int comment_cnt;
    private int like_cnt;
    List<TeamCommentDto> comment;

    public static TeamDetailResponseDto createEmpty(){return new TeamDetailResponseDto();}

    //entity를 dto로
    public static TeamDetailResponseDto of(Team team){
        TeamDetailResponseDto teamResponseDto = createEmpty();
        BeanUtils.copyProperties(team, teamResponseDto);

        /**
         * 수정해야함
         */
        List<TeamComment> collect = team.getTeamComments().stream().collect(Collectors.toList());
        List<TeamCommentDto> findComment = new ArrayList<>();
        for (TeamComment c : collect){
            findComment.add(new TeamCommentDto(c.getId(), c.getParentId(), c.getSecret(), c.getContent(), c.getCreatedAt()));
        }
        teamResponseDto.comment = findComment;

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
