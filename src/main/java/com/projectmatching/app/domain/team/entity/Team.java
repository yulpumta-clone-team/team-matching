package com.projectmatching.app.domain.team.entity;

import com.projectmatching.app.domain.BaseTimeEntity;
import com.projectmatching.app.domain.comment.entity.TeamComment;
import com.projectmatching.app.domain.user.entity.UserTeam;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
@ToString
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Table(name="team")
public class Team extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long team_id;

    @Column
    private String name;

    @Column
    private String session;

    @Column
    private String img;

    @Column
    private Long read;

    @Column(columnDefinition = "TEXT")
    private String content;


    @OneToMany
    @JoinColumn(name = "user_team")
    private Set<UserTeam> userTeams = new HashSet<>();

    @OneToMany
    @JoinColumn(name="team_comment")
    private Set<TeamComment> teamComments = new HashSet<>();


    @OneToMany
    @JoinColumn(name="team_tech")
    private Set<TeamTech> teamTeches = new HashSet<>();




}
