package com.pop.springboot.demo.starterdemo.entity;

/**
 * @program: formatter-spring-boot-starter-demo
 * @description:
 * @author: Pop
 * @create: 2019-07-10 17:33
 **/
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
