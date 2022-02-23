package com.projectmatching.app.domain.liking.entity;


import com.projectmatching.app.domain.BaseTimeEntity;
import com.projectmatching.app.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter @Setter
@ToString
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Table(name="user_liking")
public class UserLiking extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user")
    private User user;

}
