package com.projectmatching.app.domain.user.entity;

import com.projectmatching.app.domain.BaseTimeEntity;
import com.projectmatching.app.domain.comment.entity.UserComment;
import com.projectmatching.app.domain.liking.entity.UserCommentLiking;
import com.projectmatching.app.domain.liking.entity.UserLiking;
import com.projectmatching.app.domain.user.Role;
import lombok.*;

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
    @GeneratedValue
    private Long id;

    @Column
    private String oauthId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private String email;
    @Column
    private String name;
    @Column
    private String pwd;
    @Column
    private String img;
    @Column
    private String portfolio;
    @Column
    private String slogan;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private String hope_session;

    @Column
    private String job;


    @OneToMany
    @JoinColumn(name="user_liking")
    @ToString.Exclude
    private Set<UserLiking> userLikings = new HashSet<>();

    @OneToMany
    @JoinColumn(name="user_comment_liking")
    @ToString.Exclude
    private Set<UserCommentLiking> userCommentLikings = new HashSet<>();


    @OneToMany
    @JoinColumn(name="user_comment")
    @ToString.Exclude
    private Set<UserComment> userComments = new HashSet<>();


    @OneToMany
    @JoinColumn(name="user_tech")
    @ToString.Exclude
    private Set<UserTech> skills = new HashSet<>();



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
