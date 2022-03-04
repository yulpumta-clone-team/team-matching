package com.projectmatching.app.service.user;

import com.projectmatching.app.domain.user.dto.UserDto;
import com.projectmatching.app.domain.user.dto.UserLoginDto;

import javax.servlet.http.HttpServletResponse;


public interface UserSignInService {

    UserDto userLogin(UserLoginDto userLoginDto, HttpServletResponse response);

    void userDelete(String userEamil);


}
