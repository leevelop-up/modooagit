package com.example.modooagit.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 어노테이션을 입력하면 알아서 상단에 import 가 된다!
public class BoardController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}


