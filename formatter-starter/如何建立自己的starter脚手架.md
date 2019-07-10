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

这之后，我们暴露一个新的服务出去

```java
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
```

然后，如果你想要定义自己的perpies属性，需要加入这个

```xml
  <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <version>2.1.6.RELEASE</version>
      <optional>true</optional>
    </dependency>
```

```java
@ConfigurationProperties(prefix=HelloProperties.HELLO_FORMAT_PREFIX)
public class HelloProperties {

    public static final String HELLO_FORMAT_PREFIX="gupao.hello.format";
    private Map<String,Object> info;

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
}

```

然后加入这个，他的所有属性都可以点出来

```java
@Import(FormatAutoConfiguration.class)
@EnableConfigurationProperties(HelloProperties.class)
@Configuration
public class HelloAutoConfiguration {

    @Bean
    public HelloFormatTemplate helloFormatTemplate(HelloProperties helloProperties,FormatProcessor formatProcessor){
        return new HelloFormatTemplate(helloProperties,formatProcessor);
    }
}
```

看一个多数据源的配置

```java
@Configuration
public class JdbcDataSourceConfig {

    /*@Primary*/
    @Bean
    @ConfigurationProperties(prefix = "app.datasource.db1")
    public DataSourceProperties db1DataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.db2")
    public DataSourceProperties db2DataSourceProperties(){
        return new DataSourceProperties();
    }

    /*@Primary*/
    @Bean
    public DataSource db1DataSource(){
        return db1DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public DataSource db2DataSource(){
        return db2DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name="db1JdbcTemplate")
    public JdbcTemplate db1JdbcTemplate(){
        return new JdbcTemplate(db1DataSource());
    }

    @Bean(name="db2JdbcTemplate")
    public JdbcTemplate db2JdbcTemplate(){
        return new JdbcTemplate(db2DataSource());
    }



}
```

就可以这样配置

```properties

app.datasource.db1.url=jdbc:mysql://192.168.8.126:3306/db1
app.datasource.db1.username=root
app.datasource.db1.password=root
app.datasource.db1.driver-class-name=com.mysql.jdbc.Driver

app.datasource.db2.url=jdbc:mysql://192.168.8.126:3306/db2
app.datasource.db2.username=root
app.datasource.db2.password=root
app.datasource.db2.driver-class-name=com.mysql.jdbc.Driver
```

其实本质上返回的对象，就调用了相对应的get，set方法