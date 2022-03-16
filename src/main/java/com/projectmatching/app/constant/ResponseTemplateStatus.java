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



    /**
     * 4000: TeamController 팀 관련 오류
     */
    ,
    SAVE_TEAM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "팀 등록 실패", 4000),
    EMPTY_TEAM_NAME(HttpStatus.BAD_REQUEST, "팀 이름을 입력해주세요.", 4001),
    GET_TEAMS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "팀 게시글 리스트 가져오기 실패", 4002),
    INVALID_TEAM_IDX(HttpStatus.BAD_REQUEST, "유효하지 않은 team id입니다.", 4003),
    DELETE_TEAM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "팀 삭제하기 실패", 4004),
    NOT_EXIST_USER(HttpStatus.BAD_REQUEST, "존재하지 않는 유저입니다.", 4005),
    GET_TEAM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "팀 게시글 가져오기 실패", 4002)





    ,

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
