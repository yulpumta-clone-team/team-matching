package com.projectmatching.app.domain.user.service;

import com.projectmatching.app.config.resTemplate.ResponeException;
import com.projectmatching.app.config.resTemplate.ResponseTemplateStatus;
import com.projectmatching.app.domain.user.Role;
import com.projectmatching.app.domain.user.User;
import com.projectmatching.app.domain.user.UserRepository;
import com.projectmatching.app.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public void join(UserDto userDto) throws ResponeException {

        checkDuplicateEmail(userDto.getEmail());
        checkDuplicateName(userDto.getName());

        User user = User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .content(userDto.getContent())
                .hope_session(userDto.getHope_session())
                .img(userDto.getImg())
                .role(Role.USER)
                .portfolio(userDto.getPortfolio())
                .job(userDto.getJob())
                .oauthId(null)
                .pwd(passwordEncoder.encode(userDto.getPwd()))
                .slogan(userDto.getSlogan())
                .build();

        log.info("result  == {} ", user);
        userRepository.save(user);

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
}
