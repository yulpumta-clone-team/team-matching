package com.projectmatching.app.test;

import com.projectmatching.app.config.ResponseTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {



<<<<<<< HEAD
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
=======
    @GetMapping("/api")
    public String test3(@RequestBody testData data) {

        System.out.println(data.getTest());
        System.out.println(data.getArr());
        return "test api has been returned!\n";

    }


    @GetMapping("/api/test1")
    public String test() {
>>>>>>> fe0262b859eade390d287307298060c7409e8c73


        return responseTemplate;

<<<<<<< HEAD
=======
    @GetMapping("/api/test2")
    public String test2() {
>>>>>>> fe0262b859eade390d287307298060c7409e8c73


    }


}
