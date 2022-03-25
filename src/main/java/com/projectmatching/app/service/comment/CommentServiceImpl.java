package com.projectmatching.app.service.comment;

import com.projectmatching.app.config.resTemplate.ResponeException;
import com.projectmatching.app.constant.ResponseTemplateStatus;
import com.projectmatching.app.domain.comment.dto.UserCommentDto;
import com.projectmatching.app.domain.comment.repository.QTeamCommentRepository;
import com.projectmatching.app.domain.comment.repository.QUserCommentRepository;
import com.projectmatching.app.domain.comment.repository.TeamCommentRepository;
import com.projectmatching.app.domain.comment.repository.UserCommentRepository;
import com.projectmatching.app.domain.user.UserRepository;
import com.projectmatching.app.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {


    private final TeamCommentRepository teamCommentRepository;
    private final UserCommentRepository userCommentRepository;
    private final UserRepository userRepository;
    private final QTeamCommentRepository qTeamCommentRepository;
    private final QUserCommentRepository qUserCommentRepository;

    /**
     * 댓글 추가 서비스
     */
    //유저 프로필에 댓글달기

    @Override
    public UserCommentDto addUserComment(UserCommentDto userCommentDto) {
        try{
            User user = Optional.ofNullable(userRepository.getById(userCommentDto.getUserId())).orElseThrow(NullPointerException::new);
            return UserCommentDto.of(userCommentRepository.save(userCommentDto.asEntity(user)));
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new ResponeException(ResponseTemplateStatus.ADD_COMMENT_FAILED);
        }

    }


    //유저 프로필에 대댓글 달기
    @Override
    public UserCommentDto addUserNestedComment(UserCommentDto userCommentDto) {
        //부모 댓글 설정 안되어있으면 에러
        if(userCommentDto.getParentId() == null)throw new ResponeException(ResponseTemplateStatus.ADD_NESTED_FAILED);




    }

    /**
     * 댓글 수정 서비스
     */

    @Override
    public UserCommentDto updateUserComment(UserCommentDto userCommentDto) {

        userCommentRepository.findById(use)

    }

    @Override
    public UserCommentDto updateUserNestedComment(UserCommentDto userCommentDto) {
        return null;
    }














}
