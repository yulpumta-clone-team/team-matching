package com.projectmatching.app.test;

import com.projectmatching.app.config.ResponseTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {



//    @GetMapping("/")
//    public String test3(@RequestBody testData data) {
//
//        System.out.println(data.getTest());
//        System.out.println(data.getArr());
//        return "test api has been returned!\n";
//
//    }


    @GetMapping("/test")
    public ResponseTemplate<String> MsgTesting(){
        
        ResponseTemplate responseTemplate = new ResponseTemplate("안녕");


        return responseTemplate;



    }


}
