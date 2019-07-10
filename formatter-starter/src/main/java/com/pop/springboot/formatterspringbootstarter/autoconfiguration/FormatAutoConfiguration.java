package com.pop.springboot.formatterspringbootstarter.autoconfiguration;

import com.pop.springboot.formatterspringbootstarter.formatter.FormatterProcessor;
import com.pop.springboot.formatterspringbootstarter.formatter.JsonFormatterProcessor;
import com.pop.springboot.formatterspringbootstarter.formatter.StringFormatterProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @program: formatter-spring-boot-starter-demo
 * @description: 自动装配
 * @author: Pop
 * @create: 2019-07-10 17:02
 **/
@Configuration
public class FormatAutoConfiguration {


    @ConditionalOnMissingClass(value = "com.alibaba.fastjson.JSON")
    @Primary
    @Bean
    public FormatterProcessor stringFormat(){
        return new StringFormatterProcessor();
    }

    @ConditionalOnClass(name = "com.alibaba.fastjson.JSON")
    @Bean
    public FormatterProcessor jsonFormat(){
        return new JsonFormatterProcessor();
    }

}
