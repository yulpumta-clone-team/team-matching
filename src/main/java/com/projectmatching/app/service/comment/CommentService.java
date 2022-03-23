package com.projectmatching.app.service.comment;


import com.projectmatching.app.domain.comment.dto.UserCommentDto;
import com.projectmatching.app.domain.comment.entity.TeamComment;
import com.projectmatching.app.domain.comment.entity.UserComment;

import java.util.List;

public interface CommentService {

    //조회
    List<TeamComment> getTeamComment(Long teamPostId);
    List<UserComment> getUserComment(Long userPostId);

    //댓글 생성 및 수정 삭제
    Long addTeamComment(Long teamPostId);
    Long updateTeamComment(Long teamPostId, Long commentId);

    UserCommentDto addUserComment(UserCommentDto userCommentDto);
    Long updateUserComment(Long userPostId, Long comentId);

    void deleteUserComment(Long userPostId, Long commentId);
    void deleteTeamComment(Long teamPostId, Long commentId);


    //대댓글 생성 및 수정 삭제
    Long updateTeamNestedComment(Long teamPostId,Long commentId, Long childCommentId);
    Long addTeamNestedComment(Long teamPostId,Long commentId);

    Long updateUserNestedComment(Long userPostId,Long parentCommentId, Long childCommentId);
    Long addUserNestedComment(Long userPostId,Long parentCommentId);

    void deleteUserNestedComment(Long userPostId, Long parentCommentId, Long childCommentId);
    void deleteTeamNestedComment(Long userPostId, Long parentCommentId, Long childCommentId);



    



}
