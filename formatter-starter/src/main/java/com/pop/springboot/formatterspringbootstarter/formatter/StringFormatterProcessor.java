package com.pop.springboot.formatterspringbootstarter.formatter;

import java.util.Objects;

/**
 * @program: formatter-spring-boot-starter-demo
 * @description:
 * @author: Pop
 * @create: 2019-07-10 14:09
 **/
public class StringFormatterProcessor implements FormatterProcessor{
    @Override
    public <T> String format(T t) {
        return "StringFormatterProcessor: "+ Objects.toString(t);
    }
}
