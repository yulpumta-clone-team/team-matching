package com.projectmatching.app.config.resTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.projectmatching.app.constant.ResponseTemplateStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;


import static com.projectmatching.app.constant.ResponseTemplateStatus.SUCCESS;
@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess","code","message","data"})
public class ResponseTemplate<T> {
    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String message;
    private final int code; //내부 코드
    private T data;


    public static <T> ResponseTemplate<T> valueOf(T data){return of(SUCCESS,data);}

    //데이터 없음
    public static <T> ResponseTemplate<T> of(ResponseTemplateStatus status){return of(status,null);}

    //데이터 전달
    public static <T> ResponseTemplate<T> of(ResponseTemplateStatus status, T data){
        return new ResponseTemplate<>(status.isSuccess(),status.getMessage(),status.getCode(),data);

    }

    public static ResponseTemplate<Void> error(ResponseTemplateStatus status){ return of(status);}




}
