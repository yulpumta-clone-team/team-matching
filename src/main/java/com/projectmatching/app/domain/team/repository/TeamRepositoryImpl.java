package com.projectmatching.app.domain.team.repository;

import com.projectmatching.app.domain.team.dto.TeamResponseDto;
import com.projectmatching.app.domain.team.entity.QTeam;
import com.projectmatching.app.domain.team.entity.QTeamTech;
import com.projectmatching.app.domain.team.entity.Team;
import com.projectmatching.app.domain.team.entity.TeamTech;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.projectmatching.app.domain.team.entity.QTeam.team;
import static com.projectmatching.app.domain.team.entity.QTeamTech.teamTech;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@Repository
@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<TeamResponseDto> getTeams(Pageable pageable) {
        List<Team> teams = queryFactory
                .selectFrom(team)
                .leftJoin(team.teamTeches, teamTech).fetchJoin()
                .orderBy(team.team_id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Team> countQuery = queryFactory
                .select(team)
                .from(team)
                .leftJoin(team.teamTeches, teamTech).fetchJoin();

        List<TeamResponseDto> content = teams.stream()
                .map(t -> new TeamResponseDto(t.getName(), t.getSession(), t.getImg(), t.getRead(), t.getTeamTeches()))
                .collect(Collectors.toList());

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }
}
