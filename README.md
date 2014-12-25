# Woothee Spring

[Woothee](https://github.com/woothee/woothee-java) integration with Spring MVC

``` xml
<groupId>am.ik.woothee</groupId>
<artifactId>woothee-spring</artifactId>
<version>1.0.0</version>
```

## How to use

This example uses Spring Boot (+ Java Config)

``` java
import am.ik.woothee.Woothee;
import am.ik.woothee.spring.WootheeMethodArgumentResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
@RestController
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Configuration
    static class WootheeConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
            argumentResolvers.add(new WootheeMethodArgumentResolver()); // add
        }
    }

    @RequestMapping(value = "/")
    String hello(Woothee woothee /* add */) {
        System.out.println(woothee); // WootheeData{category='pc', name='Chrome', version='39.0.2171.95', os='Mac OSX', vendor=Google, osVersion=10.9.5}
        return "Hello World!";
    }
}
```

In case of XML configuration:

``` xml
<mvc:annotation-driven>
    <mvc:argument-resolvers>
        <bean
            class="am.ik.woothee.spring.WootheeMethodArgumentResolver" /><!-- add -->
    </mvc:argument-resolvers>
</mvc:annotation-driven>
```

## License

Licensed under the Apache License, Version 2.0.
