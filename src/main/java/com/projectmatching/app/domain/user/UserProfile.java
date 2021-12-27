package com.projectmatching.app.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserProfile {
    private final String oauthId;
    private final String name;
    private final String email;

    public User toUser() {
        return new User(oauthId, name, email, Role.USER);
    }





}
