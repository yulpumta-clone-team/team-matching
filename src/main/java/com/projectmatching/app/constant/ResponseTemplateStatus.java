package com.projectmatching.app.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseTemplateStatus {

    /**    private final Boolean isSuccess;
     private final String message;
     private final int code; //내부 코드
     private T data;

     *
     * 1000: 요청 성공
     */

    SUCCESS(HttpStatus.OK,"요청 성공", 1000),


    /**
     *
     * 2000 : Request 오류

     */
    EMPTY_JWT(HttpStatus.BAD_REQUEST,"JWT를 입력해주세요.",2001),
    INVALID_JWT(HttpStatus.BAD_REQUEST,"유효하지 않은 JWT입니다.",2002),



    /**
     * 3000: UserController  회원 가입, 로그인, 탈퇴 관련 오류
     */

    WITHDRAWAL_USER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"회원탈퇴 실패",3000),
    LOGIN_USER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"로그인에 실패하였습니다",3001),
    JOIN_USER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"회원가입 실패",3002),
    EMAIL_FORM_INVALID(HttpStatus.BAD_REQUEST,"이메일 형식 에러",3003),
    NAME_SIZE_INVALID(HttpStatus.BAD_REQUEST,"닉네임 형식 에러",3004),
    EMAIL_DUPLICATE(HttpStatus.BAD_REQUEST,"중복된 이메일입니다",3005),
    NAME_DUPLICATE(HttpStatus.BAD_REQUEST,"중복된 이름입니다.",3006),
    PWD_FORM_INVALID(HttpStatus.BAD_REQUEST,"비밀번호 형식 오류",3007)
    ,

    /**
     * 4000: 댓글 관련 오류
     */

    ADD_COMMENT_FAILED(HttpStatus.BAD_REQUEST,"부모 댓글인 경우에만 대댓글을 달 수 있습니다.",4000),
    ADD_NESTED_FAILED(HttpStatus.BAD_REQUEST,"부모 댓글 ID가 NULL입니다.",4001),
    UPDATE_COMMENT_FAILED(HttpStatus.BAD_REQUEST,"댓글 수정 실패",4002),

    /**
     *
     * 9000: 논리적 에러
     */
    LOGICAL_ERROR("서버 내부 논리 에러",9000);

    private HttpStatus httpStatus;
    private final String message;
    private final int code;

    private ResponseTemplateStatus(HttpStatus httpStatus, String message, int code) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }



}
