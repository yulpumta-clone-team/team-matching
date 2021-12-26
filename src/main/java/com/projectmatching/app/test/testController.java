package com.projectmatching.app.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {



    @GetMapping("/")
    public String test3(@RequestBody testData data) {

        System.out.println(data.getTest());
        System.out.println(data.getArr());
        return "test api has been returned!\n";

    }


    @GetMapping("/test")
    public String test() {

        return "test api has been returned!\n";

    }

    @GetMapping("/test2")
    public String test2() {

        return "test2 api has been returned!\n";

    }
}
