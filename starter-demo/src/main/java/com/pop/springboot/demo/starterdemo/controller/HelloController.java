package com.pop.springboot.demo.starterdemo.controller;

import com.pop.springboot.demo.starterdemo.entity.User;
import com.pop.springboot.formatterspringbootstarter.HelloFormatterTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: formatter-spring-boot-starter-demo
 * @description:
 * @author: Pop
 * @create: 2019-07-10 17:31
 **/
@RestController
public class HelloController {

    @Autowired
    HelloFormatterTemplate helloFormatterTemplate;

    @GetMapping("hello")
    public String hello(){
        return helloFormatterTemplate.doFormat(new User("Pop",18));
    }

}
