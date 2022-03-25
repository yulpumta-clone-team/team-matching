package com.projectmatching.app.service.comment;


import com.projectmatching.app.domain.comment.dto.UserCommentDto;

public interface CommentService {
//
//    //조회
//    List<TeamComment> getTeamComment(Long teamPostId);
//    List<UserComment> getUserComment(Long userPostId);
//
//    //댓글 생성 및 수정 삭제
//    Long addTeamComment(Long teamPostId);
//    Long updateTeamComment(Long teamPostId, Long commentId);
//
    UserCommentDto addUserComment(UserCommentDto userCommentDto);
    UserCommentDto updateUserComment(UserCommentDto userCommentDto);
//
//    void deleteUserComment(Long userPostId, Long commentId);
//    void deleteTeamComment(Long teamPostId, Long commentId);
//
//
//    //대댓글 생성 및 수정 삭제
//    UserCommentDto updateTeamNestedComment(Long teamPostId,Long commentId, Long childCommentId);
//    UserCommentDto addTeamNestedComment(Long teamPostId,Long commentId);
//
    UserCommentDto updateUserNestedComment(UserCommentDto userCommentDto);
    UserCommentDto addUserNestedComment(UserCommentDto userCommentDto);
//
//    void deleteUserNestedComment(Long userPostId, Long parentCommentId, Long childCommentId);
//    void deleteTeamNestedComment(Long userPostId, Long parentCommentId, Long childCommentId);



    



}
