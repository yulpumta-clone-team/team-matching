package com.projectmatching.app.domain.user;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class UserProfile {
    private final String oauthId;
    private final String name;
    private final String email;


    public User toUser() {
        return new User(oauthId, name, email, Role.USER);
    }





}
