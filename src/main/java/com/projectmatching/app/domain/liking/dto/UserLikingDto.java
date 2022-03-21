package com.projectmatching.app.domain.liking.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.projectmatching.app.domain.liking.entity.UserLiking;
import com.projectmatching.app.domain.user.dto.UserDto;
import com.projectmatching.app.domain.user.entity.User;
import com.projectmatching.app.util.IdGenerator;
import lombok.*;

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



    public UserLiking asEntity(User from , User to){
        UserLiking userLiking = new UserLiking();
        userLiking.setId(IdGenerator.number());
        to.setRespected(to.getRespected()+1);
        userLiking.setFromUser(from);
        userLiking.setToUser(to);

        return userLiking;
    }



}
