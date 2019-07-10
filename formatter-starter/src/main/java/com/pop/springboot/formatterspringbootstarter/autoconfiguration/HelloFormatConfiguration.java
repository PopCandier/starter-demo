package com.pop.springboot.formatterspringbootstarter.autoconfiguration;

import com.pop.springboot.formatterspringbootstarter.HelloFormatterTemplate;
import com.pop.springboot.formatterspringbootstarter.formatter.FormatterProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @program: formatter-spring-boot-starter-demo
 * @description:
 * @author: Pop
 * @create: 2019-07-10 17:07
 **/
@Import(FormatAutoConfiguration.class)
@Configuration
public class HelloFormatConfiguration {

    @Bean
    public HelloFormatterTemplate helloFormatterTemplate(FormatterProcessor formatterProcessor){
        return new HelloFormatterTemplate(formatterProcessor);
    }

}
