package com.exercise.test2.config;

import com.exercise.test2.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


//告诉spring这是一个配置文件
@Configuration
public class myConfig {

    //bean是单实例
    @Scope("prototype")
    @Bean("person01")
    public Person person02() {
        return new Person("song", 18);
    }

}
