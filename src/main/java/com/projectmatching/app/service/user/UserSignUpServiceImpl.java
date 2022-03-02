package com.projectmatching.app.service.user;

import com.projectmatching.app.annotation.Validation;
import com.projectmatching.app.config.resTemplate.ResponeException;
import com.projectmatching.app.constant.ResponseTemplateStatus;
import com.projectmatching.app.domain.user.entity.User;
import com.projectmatching.app.domain.user.UserRepository;
import com.projectmatching.app.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.projectmatching.app.constant.ServiceConstant.NAME_SIZE_MAX;
import static com.projectmatching.app.constant.ServiceConstant.REGEX_EMAIL;

@RequiredArgsConstructor
@Repository
public class UserSignUpServiceImpl {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Transactional
    @Validation
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

        //형식 체크
        validateEmail(userDto.getEmail());
        validateName(userDto.getName());
        //중복 체크
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



    //이메일 정규식 검증

    private void validateEmail(String email) throws ResponeException {
        if(!REGEX_EMAIL.matcher(email).matches()){
            throw new ResponeException(ResponseTemplateStatus.EMAIL_FORM_INVALID);
        }
    }

    //닉네임 검증
    private void validateName(String name)throws ResponeException{
        if(name.length() > NAME_SIZE_MAX){
            throw new ResponeException(ResponseTemplateStatus.NAME_SIZE_INVALID);
        }
    }


}
