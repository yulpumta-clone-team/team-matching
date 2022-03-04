package com.projectmatching.app.service.user;

import com.projectmatching.app.domain.user.dto.UserDto;


public interface UserSignUpService {

    Long join(UserDto userDto);

}
