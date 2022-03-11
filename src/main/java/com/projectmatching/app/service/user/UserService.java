package com.projectmatching.app.service.user;

import com.projectmatching.app.config.resTemplate.ResponeException;
import com.projectmatching.app.constant.ResponseTemplateStatus;
import com.projectmatching.app.domain.user.User;
import com.projectmatching.app.domain.user.UserRepository;
import com.projectmatching.app.domain.user.dto.UserDto;
import com.projectmatching.app.service.user.userdetail.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Transactional
    public Long join(UserDto userDto) throws ResponeException {
        try {
            checkUserValidation(userDto);
            userDto.setPwd(passwordEncoder.encode(userDto.getPwd())); //비밀번호 암호화
            User user = userDto.asEntity();
            return userRepository.save(user).getId();
        }catch (ResponeException e){
            throw e;
        }

    }

    private void checkUserValidation(UserDto userDto)throws ResponeException {
        checkDuplicateEmail(userDto.getEmail());
        checkDuplicateName(userDto.getName());

    }

    private void checkDuplicateEmail(String email) throws ResponeException {
       if(userRepository.findByEmail(email).isPresent()){
           throw new ResponeException(ResponseTemplateStatus.EMAIL_DUPLICATE);
       }
    }

    private void checkDuplicateName(String name) throws ResponeException {
        if(userRepository.findByName(name).isPresent()){
            throw new ResponeException(ResponseTemplateStatus.NAME_DUPLICATE);
        }

    }


    public void DeleteUser() throws ResponeException{
        String userEmail = getAuthUserEmail();
        userRepository.deleteUserByEmail(userEmail);

    }




    /**
     * SecurityContext에 저장되어있는 User 정보로부터 이름과 이메일을 추출함
     * @return
     */
    public String getAuthUsername() {

        UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails instanceof UserDetails){ //인증된 유저여야함
            return userDetails.getUsername();
        }else{
            log.info("인증되지 않은 유저의 정보이므로 유저 닉네임을 불러올 수 없습니다.");
            return userDetails.toString();
        }

    }


    public String getAuthUserEmail() {
        UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails instanceof UserDetails) { //인증된 유저여야함
            return userDetails.getEmail();
        }else{
            log.info("인증되지 않은 유저의 정보이므로 유저 이메일을 불러올 수 없습니다.");
            return userDetails.toString();
        }

    }

}
