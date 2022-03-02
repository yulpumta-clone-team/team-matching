package com.projectmatching.app.service.user;

import com.projectmatching.app.domain.user.dto.UserLoginDto;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public interface UserSignInService {

    Long userLogin(UserLoginDto userLoginDto, HttpServletResponse response);

    void userDelete(String userEamil);


}
