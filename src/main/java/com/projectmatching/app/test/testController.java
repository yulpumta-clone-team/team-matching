package com.projectmatching.app.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @GetMapping("/test")
    public String test() {

        return "test api has been returned!\n";

    }

    @GetMapping("/test2")
    public String test2() {

        return "test2 api has been returned!\n";

    }
}
