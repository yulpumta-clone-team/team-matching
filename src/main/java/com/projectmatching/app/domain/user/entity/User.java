package com.projectmatching.app.domain.user.entity;

import com.projectmatching.app.domain.BaseTimeEntity;
import com.projectmatching.app.domain.comment.entity.UserComment;
import com.projectmatching.app.domain.liking.entity.UserCommentLiking;
import com.projectmatching.app.domain.liking.entity.UserLiking;
import com.projectmatching.app.domain.user.Role;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@ToString
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Table(name="user")
public class User extends BaseTimeEntity  {

    @Id
    private Long id;


    private String oauthId;

    @Enumerated(EnumType.STRING)
    private Role role;


    private String email;

    private String name;

    private String pwd;

    private String img;

    private String portfolio;

    private String slogan;

    @Column(columnDefinition = "TEXT")
    private String content;


    private String hope_session;


    private String job;

    @Column(columnDefinition = "INT")
    private int respected;


    @OneToMany
    @JoinColumn(name="user_liking")
    @ToString.Exclude
    @Builder.Default
    private Set<UserLiking> userLikings = new HashSet<>();

    @OneToMany
    @JoinColumn(name="user_comment_liking")
    @ToString.Exclude
    @Builder.Default
    private Set<UserCommentLiking> userCommentLikings = new HashSet<>();


    @OneToMany
    @JoinColumn(name="user_comment")
    @ToString.Exclude
    @Builder.Default
    private Set<UserComment> userComments = new HashSet<>();


    @OneToMany
    @JoinColumn(name="user_tech")
    @ToString.Exclude
    @Builder.Default
    private Set<UserTech> skills = new HashSet<>();


    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<UserTeam> userTeams = new HashSet<>();

    public User(String oauthId,String name, String email, Role role ){
        this.id = null;
        this.oauthId = oauthId;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public String getRolekey(){
        return this.role.getKey();
    }


    public User update(String name, String email){
        this.name = name;
        this.email = email;
        return this;
    }







}
