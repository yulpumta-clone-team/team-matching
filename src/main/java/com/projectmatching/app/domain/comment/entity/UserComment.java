package com.projectmatching.app.domain.comment.entity;


import com.projectmatching.app.domain.BaseTimeEntity;
import com.projectmatching.app.domain.liking.entity.UserCommentLiking;
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
@Table(name = "user_comment")
public class UserComment extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="parent_id")
    private Long parentId;

    private Boolean secret;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;


    @OneToMany
    @JoinColumn(name="user_comment_liking")
    @ToString.Exclude
    private Set<UserCommentLiking> userCommentLikings = new HashSet<>();


}
