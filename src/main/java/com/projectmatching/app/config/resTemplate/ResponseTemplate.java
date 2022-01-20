package com.projectmatching.app.config.resTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.projectmatching.app.config.resTemplate.ResponseTemplateStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.projectmatching.app.config.resTemplate.ResponseTemplateStatus.*;
@Getter
@AllArgsConstructor
@JsonPropertyOrder({"status","isSuccess","code","message","data"})
public class ResponseTemplate<T> {
    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final int status;
    private final String message;
    private final int code; //내부 코드
    private T data;
    
    //요청 성공시
    public ResponseTemplate(T data){
        this.isSuccess = SUCCESS.isSuccess();
        this.status = HttpStatus.OK.value();
        this.message = SUCCESS.getMessage();
        this.code = SUCCESS.getCode();
        this.data = data;

    }


    //요청 실패시
    public ResponseTemplate(ResponseTemplateStatus status, HttpStatus httpStatus){
        this.isSuccess = status.isSuccess();
        this.status = httpStatus.value();
        this.message = status.getMessage();
        this.code = status.getCode();

    }



}
