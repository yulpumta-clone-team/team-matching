package com.projectmatching.app.test;

import com.projectmatching.app.config.resTemplate.ResponseTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {


    @GetMapping("/test")
    public ResponseTemplate<String> MsgTesting() {

        return new ResponseTemplate<>("안녕");
    }
}

