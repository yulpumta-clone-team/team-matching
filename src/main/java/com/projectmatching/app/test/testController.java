package com.projectmatching.app.test;

import com.projectmatching.app.config.ResponseTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {


    @GetMapping("/test")
    public ResponseTemplate<String> MsgTesting() {

        return new ResponseTemplate<>("안녕");
    }
}

