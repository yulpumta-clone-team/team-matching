package com.projectmatching.app.service.user;

import com.projectmatching.app.annotation.Validation;
import com.projectmatching.app.domain.user.QUserRepository;
import com.projectmatching.app.domain.user.UserRepository;
import com.projectmatching.app.domain.user.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignInService {

    private final UserRepository userRepository;
    private final QUserRepository qUserRepository;
    //유저 로그인
    @Transactional(readOnly = true)
    @Validation
    public Long userLogin(UserLoginDto userLoginDto){

        return qUserRepository.login(userLoginDto);
    }
}
