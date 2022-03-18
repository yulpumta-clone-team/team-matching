package com.projectmatching.app.domain.comment.entity;

import com.projectmatching.app.domain.BaseTimeEntity;
import com.projectmatching.app.domain.liking.entity.TeamCommentLiking;
import com.projectmatching.app.domain.team.entity.Team;
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

    @Column(name="parent_id")
    private Long parentId;

    private Boolean secret;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")
    private Team team;

    @OneToMany
    @JoinColumn(name = "team_comment_liking")
    @Builder.Default
    private Set<TeamCommentLiking> teamCommentLikings = new HashSet<>();



}
