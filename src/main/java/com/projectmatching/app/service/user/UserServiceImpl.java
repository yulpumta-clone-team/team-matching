package com.projectmatching.app.service.user;

import com.projectmatching.app.domain.user.QUserRepository;
import com.projectmatching.app.domain.user.UserRepository;
import com.projectmatching.app.domain.user.dto.UserDto;
import com.projectmatching.app.domain.user.dto.UserProfileDto;
import com.projectmatching.app.exception.CoNectRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private final QUserRepository qUserRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDto getUserDetail(Long id){
        return UserDto.of(qUserRepository.find(id)
                .orElseThrow(CoNectRuntimeException::new)
        );

    }


    //유저 업데이트
    @Transactional
    @Override
    public Long updateUser(UserDto NewUserDto) {
        String userEmail = this.getAuthUserEmail();
        UserDto DBUser =  Optional.ofNullable(userRepository.findByEmail(userEmail))
              .map(u -> UserDto.of(u.get())).orElse(UserDto.createEmpty());
        BeanUtils.copyProperties(NewUserDto,DBUser);

       return userRepository.save(DBUser.asEntity()).getId();

    }
    @Transactional(readOnly = true)
    @Override
    public List<UserProfileDto> getUserList(PageRequest pageRequest){
        return qUserRepository.find(pageRequest)
                .stream().map(UserProfileDto::of)
                .collect(Collectors.toList());
    }




}
