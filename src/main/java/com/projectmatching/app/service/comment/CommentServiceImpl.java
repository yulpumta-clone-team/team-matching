package com.projectmatching.app.service.comment;

import com.projectmatching.app.domain.comment.entity.TeamComment;
import com.projectmatching.app.domain.comment.entity.UserComment;
import com.projectmatching.app.domain.comment.repository.QTeamCommentRepository;
import com.projectmatching.app.domain.comment.repository.QUserCommentRepository;
import com.projectmatching.app.domain.comment.repository.TeamCommentRepository;
import com.projectmatching.app.domain.comment.repository.UserCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {


    private final TeamCommentRepository teamCommentRepository;
    private final UserCommentRepository userCommentRepository;

    private final QTeamCommentRepository qTeamCommentRepository;
    private final QUserCommentRepository qUserCommentRepository;

    @Override
    public List<TeamComment> getTeamComment(Long teamPostId) {
        return null;
    }

    @Override
    public List<UserComment> getUserComment(Long userPostId) {
        return null;
    }

    @Override
    public Long addTeamComment(Long teamPostId) {
        return null;
    }

    @Override
    public Long updateTeamComment(Long teamPostId, Long commentId) {
        return null;
    }

    @Override
    public Long addUserComment(String content,Long userPostId) {


        return null;
    }

    @Override
    public Long updateUserComment(Long userPostId, Long comentId) {
        return null;
    }

    @Override
    public void deleteUserComment(Long userPostId, Long commentId) {

    }

    @Override
    public void deleteTeamComment(Long teamPostId, Long commentId) {

    }

    @Override
    public Long updateTeamNestedComment(Long teamPostId, Long commentId, Long childCommentId) {
        return null;
    }

    @Override
    public Long addTeamNestedComment(Long teamPostId, Long commentId) {
        return null;
    }

    @Override
    public Long updateUserNestedComment(Long userPostId, Long parentCommentId, Long childCommentId) {
        return null;
    }

    @Override
    public Long addUserNestedComment(String content,Long userPostId, Long parentCommentId) {
        UserComment userComment = userCommentRepository.getUserCommentByPostId(userPostId);

        return null;
    }

    @Override
    public void deleteUserNestedComment(Long userPostId, Long parentCommentId, Long childCommentId) {

    }

    @Override
    public void deleteTeamNestedComment(Long userPostId, Long parentCommentId, Long childCommentId) {

    }
}
