package com.projectmatching.app.domain.team.entity;

import com.projectmatching.app.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@NoArgsConstructor
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




}
