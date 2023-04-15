package com.example.modooagit.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller // 어노테이션을 입력하면 알아서 상단에 import 가 된다!
@RestController
public class BoardController {
    //@RequestMapping("/test")
    @GetMapping(value="/api/helloWorld")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}


