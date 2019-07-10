package com.pop.springboot.formatterspringbootstarter.formatter;


public interface FormatterProcessor {
    /**
     * 任意格式化的方法
     * @param t
     * @param <T>
     * @return
     */
    <T> String format(T t);
}
