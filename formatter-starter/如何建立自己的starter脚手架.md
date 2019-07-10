#### 如何建立自己的starter脚手架

starter的制作主要是为了更加方便的整合入spring-boot，其实也就是让自己的bean整合入spring的IOC容器中，让spring托管，所以实现的本质是自动装配。

我们随便定义一个功能。

```java
public interface FormatterProcessor {
    /**
     * 任意格式化的方法
     * @param t
     * @param <T>
     * @return
     */
    <T> String format(T t);
}
```

并随便提供两个实现的方法，一个是通过fastJson将对象转化成string，一个是利用spring中的工具类，将对象转化成string

```xml
<dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.28</version>
            <optional>true</optional><!--可选-->
        </dependency>
```

两种实现

```java
public class JsonFormatterProcessor implements FormatterProcessor {
    @Override
    public <T> String format(T t) {
        return "JsonFormatterProcessor"+ JSON.toJSONString(t);
    }
}

public class StringFormatterProcessor implements FormatterProcessor{
    @Override
    public <T> String format(T t) {
        return "StringFormatterProcessor: "+ Objects.toString(t);
    }
}
```

