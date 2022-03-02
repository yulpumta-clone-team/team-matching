package com.projectmatching.app.service.user;

import com.projectmatching.app.domain.user.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserSignUpService {

    Long join(UserDto userDto);

}
