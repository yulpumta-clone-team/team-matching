package com.projectmatching.app.service.comment;

import com.projectmatching.app.config.resTemplate.ResponeException;
import com.projectmatching.app.constant.ResponseTemplateStatus;
import com.projectmatching.app.domain.comment.dto.UserCommentDto;
import com.projectmatching.app.domain.comment.entity.UserComment;
import com.projectmatching.app.domain.comment.repository.QTeamCommentRepository;
import com.projectmatching.app.domain.comment.repository.QUserCommentRepository;
import com.projectmatching.app.domain.comment.repository.TeamCommentRepository;
import com.projectmatching.app.domain.comment.repository.UserCommentRepository;
import com.projectmatching.app.domain.user.Role;
import com.projectmatching.app.domain.user.UserRepository;
import com.projectmatching.app.domain.user.entity.User;
import com.projectmatching.app.service.user.userdetail.UserDetailsImpl;
import com.projectmatching.app.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Transactional(rollbackFor = ResponeException.class)
    public UserCommentDto addUserComment(UserCommentDto userCommentDto) {

        return UserCommentDto.of(addCommentToUser(userCommentDto));

    }

    //유저 프로필에 대댓글 달기
    @Override
    @Transactional(rollbackFor = ResponeException.class)
    public UserCommentDto addUserNestedComment( UserCommentDto userCommentDto) {
        //부모 댓글 설정 안되어있으면 에러
        try {
            if (userCommentDto.getParentId() == null) throw new ResponeException(ResponseTemplateStatus.ADD_NESTED_FAILED);
            UserComment userComment = addCommentToUser(userCommentDto);
            userComment.setParent(userCommentRepository.findById(userCommentDto.getParentId()).orElseThrow(NullPointerException::new)); //부모 댓글 설정
            return userCommentDto.of(userCommentRepository.save(userComment));

        }catch (RuntimeException e){
            e.printStackTrace();
            throw new ResponeException(ResponseTemplateStatus.ADD_NESTED_FAILED);
        }


    }


    /**
     * 댓글 수정 서비스
     */
    @Override
    @Transactional(rollbackFor = ResponeException.class)
    public UserCommentDto updateUserComment(UserCommentDto userCommentDto) {

        return userCommentDto.of(updateCommentToUser(userCommentDto));


    }

    /**
     * 대댓글 수정
     */
    @Override
    @Transactional(rollbackFor = ResponeException.class)
    public UserCommentDto updateUserNestedComment(UserCommentDto userCommentDto) {
        return userCommentDto.of(updateCommentToUser(userCommentDto));
    }


    /**
     *  (대)댓글 삭제
     */

    @Transactional(rollbackFor = ResponeException.class)
    @Override
    public void deleteUserComment(UserDetailsImpl userDetails, Long commentId) {

        UserComment userComment = Optional.of(userCommentRepository.getById(commentId)).orElseThrow(NullPointerException::new);
        //작성자와 삭제자 일치하거나 유저프로필이 본인 것이거나 관리자 일경우에만 삭제
        if(userComment.getUser().getName().equals(userDetails.getUserRealName()) || userComment.getWriter().equals(userDetails.getUserRealName()) || userDetails.getRole().equals(Role.ADMIN))
            userCommentRepository.delete(userComment);

        else throw new ResponeException(ResponseTemplateStatus.DELETE_COMMENT_FAILED);

    }


    /**
     * 유저게시물에서 댓글 리스트 조회
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserCommentDto> getUserComment(Long userPostId) {

        List<UserCommentDto> userComments = userCommentRepository.getUserCommentByPostId(userPostId).stream()
                .map(UserCommentDto::of)
                .collect(Collectors.toList());

        return userComments;

    }

    private UserComment updateCommentToUser(UserCommentDto userCommentDto){
        try {
            UserComment userComment = userCommentRepository.findById(userCommentDto.getUserId()).orElseThrow(NullPointerException::new);
            //부모 댓글이 바뀌면 안됨
            if(userCommentDto.getParentId() != userComment.getParent().getId()) throw new RuntimeException();

            userComment.setContent(userComment.getContent()); //댓글 수정
            if(userCommentDto.getSecret() != userComment.getSecret()) userComment.setSecret(userCommentDto.getSecret()); //비밀댓글 여부 바뀌었다면
            return userComment;

        }catch (RuntimeException e){
            e.printStackTrace();
            throw new ResponeException(ResponseTemplateStatus.UPDATE_COMMENT_FAILED);
        }

    }


    private UserComment addCommentToUser(UserCommentDto userCommentDto) {
        try{
            User user = Optional.ofNullable(userRepository.getById(userCommentDto.getUserId())).orElseThrow(NullPointerException::new);
            userCommentDto.setId(IdGenerator.number()); //새로운 댓글 id 생성
            UserComment userComment = userCommentDto.asEntity();
            userComment.setUser(user);
            userCommentRepository.save(userComment);
            return userComment;
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new ResponeException(ResponseTemplateStatus.ADD_COMMENT_FAILED);
        }
    }


}
