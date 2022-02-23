package com.projectmatching.app.domain.user.dto;

import com.projectmatching.app.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter @Setter

@NoArgsConstructor
public class UserProfileDto {

    private String name;
    private String slogan;
    private String description;
    private String img;
    private String hope_session;
    private List<String> skills;
    private String job;
    private String status; //현재 상태 (유저가 수동으로 변경, 상태가 Closed 일 경우 인력시장에서 사라짐)

    private Long commentCnt;
    private Long likeCnt;


    public static UserProfileDto createEmpty(){return new UserProfileDto();}

    //entity를 dto로
    public static UserProfileDto of(User user){
        UserProfileDto userProfileDto = createEmpty();
        BeanUtils.copyProperties(user, userProfileDto);
        /**
         * Todo
         * Cnt 정보 set
         */

        return userProfileDto;
    }

    //dto를 entity로
    public User asEntity(){
        User user = new User();
        BeanUtils.copyProperties(this,user);
        return user;

    }

}
