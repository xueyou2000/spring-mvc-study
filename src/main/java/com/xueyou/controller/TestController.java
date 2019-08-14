package com.xueyou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/hello2")
    public String hello() {
        return "welcome";
    }

}
