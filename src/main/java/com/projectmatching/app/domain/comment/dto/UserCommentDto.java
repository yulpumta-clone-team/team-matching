package com.projectmatching.app.domain.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projectmatching.app.domain.comment.entity.UserComment;
import com.projectmatching.app.domain.liking.entity.UserLiking;
import com.projectmatching.app.util.IdGenerator;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCommentDto {

    private Long id = IdGenerator.number();
    private Long userId;
    private Long parentId;
    private Boolean secret;
    private String content;
    private String status;

    @Builder.Default
    private List<UserCommentDto> comments = new ArrayList<>();
    @Builder.Default
    private List<UserLiking> feelings = new ArrayList<>();


    public static UserCommentDto createEmpty() { return new UserCommentDto();}

    //entity를 dto로
    public UserCommentDto of(UserComment userComment){
        UserCommentDto userCommentDto = createEmpty();
        BeanUtils.copyProperties(userComment,userCommentDto);
        userCommentDto.userId = userComment.getUser().getId();
        //부모가 있다면, 즉 대댓글이라면
        if(userComment.hasParent()){
            userCommentDto.parentId = userComment.getParent().getId();
        }
        if(userComment.hasChildren()){
            userCommentDto.comments = userComment.getComments()
                    .stream().map(userCommentDto::of).collect(Collectors.toList());
        }
        return userCommentDto;
    }

    //dto를 entity로
    public UserComment asEntity(){
        UserComment userComment = new UserComment();
        BeanUtils.copyProperties(this, userComment);
        return userComment;

    }

}
