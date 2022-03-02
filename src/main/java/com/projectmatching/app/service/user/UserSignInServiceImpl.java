package com.projectmatching.app.service.user;

import com.projectmatching.app.annotation.Validation;
import com.projectmatching.app.config.resTemplate.ResponeException;
import com.projectmatching.app.domain.user.QUserRepository;
import com.projectmatching.app.domain.user.UserRepository;
import com.projectmatching.app.domain.user.dto.UserLoginDto;
import com.projectmatching.app.domain.user.entity.User;
import com.projectmatching.app.util.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.projectmatching.app.constant.ResponseTemplateStatus.LOGIN_USER_ERROR;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSignInServiceImpl {

    private final UserRepository userRepository;
    private final QUserRepository qUserRepository;
    private final AuthTokenProvider jwtTokenProvider;

    //유저 로그인

    /**
     * 해당 유저 존재하면
     * 토큰을 만들어 헤더에 저장하고
     * 유저의 id값 반환
     */
    @Transactional(readOnly = true)
    @Validation
    public Long userLogin(UserLoginDto userLoginDto, HttpServletResponse response){
        try {
            Optional<User> user = Optional.of(qUserRepository.login(userLoginDto));
            user.ifPresent(ret->jwtTokenProvider.createCookie(response, jwtTokenProvider.createToken(ret)));
            return user.get().getId();
        }catch (NullPointerException e){
            throw new ResponeException(LOGIN_USER_ERROR);
        }
    }

    @Transactional
    public void userDelete(String userEamil){
        qUserRepository.deleteUser(userEamil);
        log.info("유저 삭제됨 user email = {}",userEamil);
    }

}
