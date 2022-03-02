package com.projectmatching.app.service.user;

import com.projectmatching.app.domain.user.dto.UserDto;
import com.projectmatching.app.domain.user.dto.UserProfileDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    //유저 상세 조회
    UserDto getUserDetail(Long id);
    //유저 카드 조회
    List<UserProfileDto> getUserList(PageRequest pageRequest);


}
