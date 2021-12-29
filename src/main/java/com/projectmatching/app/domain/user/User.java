package com.projectmatching.app.domain.user;

import com.projectmatching.app.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String oauthId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String pwd;


    public User(String oauthId,String name, String email,Role role ){
        this.oauthId = oauthId;
        this.name = name;
        this.email = email;
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
