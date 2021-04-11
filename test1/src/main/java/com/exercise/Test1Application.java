package com.exercise;

import com.exercise.test2.bean.Person;
import com.exercise.test2.config.myConfig;
import javafx.application.Application;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication
@ServletComponentScan
@MapperScan("com.exercise.testspringboot.mapper")
public class Test1Application {

    public static void main(String[] args) {

        SpringApplication.run(Test1Application.class, args);

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(myConfig.class);
        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean);

        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        for(String str : beanNamesForType) {
            System.out.println(str);
        }

        Object person01 = applicationContext.getBean("person01");
        Object person02 = applicationContext.getBean("person01");
        System.out.println(person01==person02);


    }

}
