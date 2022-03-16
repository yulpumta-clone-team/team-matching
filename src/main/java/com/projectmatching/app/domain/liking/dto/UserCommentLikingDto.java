package com.projectmatching.app.domain.liking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projectmatching.app.domain.liking.entity.UserCommentLiking;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCommentLikingDto {


    private Long userId;

    public static UserCommentLikingDto of(UserCommentLiking userCommentLiking){
        UserCommentLikingDto userLikingDto = new UserCommentLikingDto();
        userLikingDto.userId = userCommentLiking.getUser().getId();
        return userLikingDto;
    }
}
