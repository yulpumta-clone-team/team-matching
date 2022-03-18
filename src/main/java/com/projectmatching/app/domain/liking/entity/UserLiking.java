package com.projectmatching.app.domain.liking.entity;


import com.projectmatching.app.domain.BaseTimeEntity;
import com.projectmatching.app.domain.comment.entity.UserComment;
import com.projectmatching.app.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name="user_liking")
public class UserLiking extends BaseTimeEntity {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    @ToString.Exclude
    private UserComment userComment;

}
