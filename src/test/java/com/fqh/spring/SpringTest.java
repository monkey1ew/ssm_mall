package com.fqh.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 海盗狗
 * @version 1.0
 */
public class SpringTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-application.xml");

        String[] definitionNames = context.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }
}
