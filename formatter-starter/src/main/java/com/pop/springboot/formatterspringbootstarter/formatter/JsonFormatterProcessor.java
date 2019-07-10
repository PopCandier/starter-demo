package com.pop.springboot.formatterspringbootstarter.formatter;

import com.alibaba.fastjson.JSON;

/**
 * @program: formatter-spring-boot-starter-demo
 * @description:
 * @author: Pop
 * @create: 2019-07-10 14:05
 **/
public class JsonFormatterProcessor implements FormatterProcessor {
    @Override
    public <T> String format(T t) {
        return "JsonFormatterProcessor"+ JSON.toJSONString(t);
    }
}
