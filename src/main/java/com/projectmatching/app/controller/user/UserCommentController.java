package com.projectmatching.app.controller.user;

import com.projectmatching.app.config.resTemplate.ResponseTemplate;
import com.projectmatching.app.constant.ResponseTemplateStatus;
import com.projectmatching.app.domain.comment.dto.UserCommentDto;
import com.projectmatching.app.service.comment.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/comment")
@Slf4j
@RequiredArgsConstructor
@Api(tags = "유저 댓글 컨트롤러")
public class UserCommentController {

    private final CommentService commentService;

    @ApiOperation(value ="유저 댓글 달기")
    @PostMapping("")
    public ResponseTemplate<Void> addComment(@RequestBody UserCommentDto userCommentDto){

        commentService.addUserComment(userCommentDto);
        return ResponseTemplate.of(ResponseTemplateStatus.SUCCESS);

    }

}
