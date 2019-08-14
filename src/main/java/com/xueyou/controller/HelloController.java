package com.xueyou.controller;

import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping("/profile")
    public Map<String, Object> profile() {
        Map<String, Object> map = new ManagedMap<>();
        map.put("name", "XueYou");
        map.put("age", 99);
        map.put("createDate", LocalDateTime.now());
        return map;
    }

}
