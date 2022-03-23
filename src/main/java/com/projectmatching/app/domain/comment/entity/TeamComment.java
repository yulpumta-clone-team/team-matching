package com.projectmatching.app.domain.comment.entity;

import com.projectmatching.app.domain.BaseTimeEntity;
import com.projectmatching.app.domain.liking.entity.TeamCommentLiking;
import com.projectmatching.app.domain.team.entity.Team;
import com.projectmatching.app.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = false)
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Table(name = "team_comment")
public class TeamComment extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private TeamComment parent;

    private Boolean secret;

    private String content;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")
    private Team team;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ToString.Exclude
    @OneToMany(mappedBy = "parent")
    @Builder.Default
    private Set<TeamComment> comments = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "teamComment", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<TeamCommentLiking> teamCommentLikings = new HashSet<>();



}
