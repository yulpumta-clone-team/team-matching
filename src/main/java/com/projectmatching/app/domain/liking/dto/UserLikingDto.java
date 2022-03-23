package com.projectmatching.app.domain.liking.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.projectmatching.app.domain.liking.entity.UserLiking;
import com.projectmatching.app.domain.user.dto.UserDto;
import com.projectmatching.app.domain.user.entity.User;
import com.projectmatching.app.util.IdGenerator;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLikingDto {

    private Long id = IdGenerator.number();
    private UserDto fromUser;
    private UserDto toUser;



    public static UserLikingDto of(User from, User to){

        UserLikingDto userLikingDto = new UserLikingDto();
        userLikingDto.fromUser = UserDto.of(from);
        userLikingDto.toUser = UserDto.of(to);
        return userLikingDto;

    }


    public UserLiking asEntity(){
        UserLiking userLiking = new UserLiking();
        BeanUtils.copyProperties(this,userLiking);
        return userLiking;
    }



}
