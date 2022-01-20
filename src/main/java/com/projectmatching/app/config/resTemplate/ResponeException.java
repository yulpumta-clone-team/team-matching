package com.projectmatching.app.config.resTemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponeException extends Exception{
    private ResponseTemplateStatus status;
}
