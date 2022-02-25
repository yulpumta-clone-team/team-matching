package com.projectmatching.app.service.user;

import com.projectmatching.app.annotation.Validation;
import com.projectmatching.app.config.resTemplate.ResponeException;
import com.projectmatching.app.domain.user.QUserRepository;
import com.projectmatching.app.domain.user.UserRepository;
import com.projectmatching.app.domain.user.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.projectmatching.app.constant.ResponseTemplateStatus.LOGIN_USER_ERROR;

@Service
@RequiredArgsConstructor
public class UserSignInService {

    private final UserRepository userRepository;
    private final QUserRepository qUserRepository;

    //유저 로그인
    @Transactional(readOnly = true)
    @Validation
    public Optional<Long> userLogin(UserLoginDto userLoginDto){
        try {
            return Optional.of(qUserRepository.login(userLoginDto));
        }catch (Exception e){
            throw new ResponeException(LOGIN_USER_ERROR);
        }
    }
}
