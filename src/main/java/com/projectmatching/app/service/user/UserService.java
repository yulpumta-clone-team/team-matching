package com.projectmatching.app.service.user;

import com.projectmatching.app.config.resTemplate.ResponeException;
import com.projectmatching.app.domain.user.QUserRepository;
import com.projectmatching.app.domain.user.UserRepository;
import com.projectmatching.app.domain.user.dto.UserDto;
import com.projectmatching.app.domain.user.dto.UserProfileDto;
import com.projectmatching.app.exception.CoNectRuntimeException;
import com.projectmatching.app.service.user.userdetail.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {


    private final UserRepository userRepository;
    private final QUserRepository qUserRepository;


    //유저 상세 조회
    @Transactional(readOnly = true)
    public UserDto getUserDetail(Long id){
        return UserDto.of(qUserRepository.find(id)
                .orElseThrow(CoNectRuntimeException::new)
        );

    }


    //유저 카드 조회
    @Transactional(readOnly = true)
    public List<UserProfileDto> getUserList(PageRequest pageRequest){
        return qUserRepository.find(pageRequest)
                .stream().map(UserProfileDto::of)
                .collect(Collectors.toList());
    }



    //유저 삭제
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
