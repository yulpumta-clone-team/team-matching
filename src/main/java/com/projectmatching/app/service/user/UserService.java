package com.projectmatching.app.service.user;

import com.projectmatching.app.domain.user.dto.UserDto;
import com.projectmatching.app.domain.user.dto.UserProfileDto;
import com.projectmatching.app.service.user.userdetail.UserDetailsImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {
    //유저 상세 조회
    UserDto getUserDetail(Long id);
    //유저 카드 조회
    List<UserProfileDto> getUserList(PageRequest pageRequest);

    //유저 업데이트
    Long updateUser(UserDto userDto);

    //특정 유저 좋아요 누르기
    Long addLiking(UserDetailsImpl userDetails, long userId);



    /**
     * SecurityContext에 저장되어있는 User 정보로부터 이름과 이메일을 추출함
     * @return
     */
    default String getAuthUsername() {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails instanceof UserDetails){ //인증된 유저여야함
            return userDetails.getUsername();
        }else{
//            log.info("인증되지 않은 유저의 정보이므로 유저 닉네임을 불러올 수 없습니다.");
            return userDetails.toString();
        }

    }

    default String getAuthUserEmail() {
        UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails instanceof UserDetails) { //인증된 유저여야함
            return userDetails.getEmail();
        }else{
//            log.info("인증되지 않은 유저의 정보이므로 유저 이메일을 불러올 수 없습니다.");
            return userDetails.toString();
        }

    }


}
