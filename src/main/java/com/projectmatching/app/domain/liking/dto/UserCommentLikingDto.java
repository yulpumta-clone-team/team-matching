package com.projectmatching.app.domain.liking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projectmatching.app.domain.liking.entity.UserCommentLiking;
import com.projectmatching.app.util.IdGenerator;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
/**
 * 해당 댓글 좋아요한 유저 id목록
 */
public class UserCommentLikingDto {


    private Long userId = IdGenerator.number();

    public static UserCommentLikingDto of(UserCommentLiking userCommentLiking){
        UserCommentLikingDto userLikingDto = new UserCommentLikingDto();
        userLikingDto.userId = userCommentLiking.getUser().getId();
        return userLikingDto;
    }
}
