package com.projectmatching.app.controller.user;
import com.projectmatching.app.config.resTemplate.ResponeException;
import com.projectmatching.app.config.resTemplate.ResponseTemplate;
import com.projectmatching.app.domain.user.UserRepository;
import com.projectmatching.app.domain.user.dto.UserDto;
import com.projectmatching.app.service.user.UserService;
import com.projectmatching.app.service.user.UserSignUpService;
import com.projectmatching.app.util.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.projectmatching.app.constant.ResponseTemplateStatus.JOIN_USER_ERROR;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Deprecated
    private final PasswordEncoder passwordEncoder;

    private final AuthTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserSignUpService userSignUpService;


    /**
     * 일반 회원가입
     * 가입 성공시 유저 id반환
     * @param userDto
     * @param result
     * @return
     * @throws ResponeException
     */
    @PostMapping("/join")
    public ResponseTemplate<Long> join(@RequestBody @Valid UserDto userDto, BindingResult result) throws ResponeException {

        if(result.hasErrors()) return ResponseTemplate.valueOf(userSignUpService.join(userDto));
        return ResponseTemplate.of(JOIN_USER_ERROR);

    }





    /**
     * 로그인
     */
//    @PostMapping("/login")
//    public void login(@RequestBody @Valid UserLoginDto user, HttpServletResponse response,BindingResult result) {
//
//        if(!result.hasErrors()){
//            User member = userRepository.findByEmail(user.getEmail())
//                    .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
//            if (!passwordEncoder.matches(user.getPwd(), member.getPwd())) {
//                throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//            }
//            String token = jwtTokenProvider.createToken(member.getName(), member.getRole());
//            jwtTokenProvider.createCookie(response,token); //쿠키 발급
//        }
//        else{
//            //에러 List로 저장
//            List<ObjectError> list = result.getAllErrors();
//            for(ObjectError error : list){
//                log.info("{}",error);
//            }
//        }
//
//    }



//    /**
//     * 회원탈퇴
//     */
//    @DeleteMapping("/user/withdrawal")
//    public ResponseTemplate<String> withDrawal(){
//        try {
//            userService.DeleteUser();
//            return ResponseTemplate.of(SUCCESS);
//        }catch (ResponeException e){
//            return ResponseTemplate.of(WITHDRAWAL_USER_ERROR);
//        }
//    }



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
