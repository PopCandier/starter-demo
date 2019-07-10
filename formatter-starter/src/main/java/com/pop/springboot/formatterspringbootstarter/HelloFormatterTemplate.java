package com.pop.springboot.formatterspringbootstarter;

import com.pop.springboot.formatterspringbootstarter.formatter.FormatterProcessor;

/**
 * @program: formatter-spring-boot-starter-demo
 * @description: 一个对springboot开放的类
 * @author: Pop
 * @create: 2019-07-10 16:42
 **/
public class HelloFormatterTemplate {

    private FormatterProcessor formatterProcessor;

    public HelloFormatterTemplate(FormatterProcessor formatterProcessor) {
        this.formatterProcessor = formatterProcessor;
    }

    public <T> String doFormat(T obj){

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("begin:Execute format").append("<br/>");
//        stringBuilder.append("HelloProperties:").append(formatProcessor.format(helloProperties.getInfo())).append("<br/>");
        stringBuilder.append("Obj format result:").append(formatterProcessor.format(obj)).append("<br/>");
        return stringBuilder.toString();

    }
}
