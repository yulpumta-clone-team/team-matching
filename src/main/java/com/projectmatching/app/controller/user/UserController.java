package com.projectmatching.app.controller.user;

import com.projectmatching.app.config.resTemplate.ResponeException;
import com.projectmatching.app.config.resTemplate.ResponseTemplate;
import com.projectmatching.app.domain.user.UserRepository;
import com.projectmatching.app.domain.user.dto.UserDto;
import com.projectmatching.app.domain.user.dto.UserLoginDto;
import com.projectmatching.app.service.user.UserService;
import com.projectmatching.app.service.user.UserSignInService;
import com.projectmatching.app.service.user.UserSignUpService;
import com.projectmatching.app.util.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static com.projectmatching.app.constant.ResponseTemplateStatus.SUCCESS;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Deprecated
    private final PasswordEncoder passwordEncoder;

    private final AuthTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserSignUpService userSignUpService;
    private final UserSignInService userSignInService;


    /**
     * 일반 회원가입
     * 가입 성공시 유저 id반환
     */
    @PostMapping("/join")
    public ResponseTemplate<Long> join(@RequestBody UserDto userDto) throws ResponeException {

         return ResponseTemplate.valueOf(userSignUpService.join(userDto));

    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseTemplate<?> login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) {
            return ResponseTemplate.valueOf(userSignInService.userLogin(userLoginDto,response));
    }



    /**
     * 회원탈퇴
     */
    @DeleteMapping("/withdrawal")
    public ResponseTemplate<String> withDrawal(){
        userSignInService.userDelete(userService.getAuthUserEmail());
        return ResponseTemplate.of(SUCCESS);

    }



//    /**
//     * 유저 프로필 최초 생성
//     * @param userProfileDto
//     * @return
//     */
//    @PostMapping("/user/myprofile")
//    public ResponseTemplate<> createUserProfile(@RequestBody UserProfileDto userProfileDto){
//
//
//    }



}
