package com.projectmatching.app.domain.techStack.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class TechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long techStackId;

    @Column
    private String category;

    @Column
    private String name; //기술 이름
}
