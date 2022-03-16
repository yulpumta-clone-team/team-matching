package com.projectmatching.app.domain.techStack.entity;

import com.projectmatching.app.domain.team.entity.TeamTech;
import com.projectmatching.app.domain.user.entity.UserTech;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "tech_stack")
public class TechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long techStackId;

    @Column
    private String category;

    @Column
    private String name; //기술 이름


    @OneToMany(mappedBy = "techStack", cascade = CascadeType.ALL)
    private Set<TeamTech> teamTechs = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "user_tech")
    @ToString.Exclude
    private Set<UserTech> userTeches = new HashSet<>();


}
