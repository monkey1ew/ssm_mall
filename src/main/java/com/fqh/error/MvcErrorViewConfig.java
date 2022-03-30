package com.fqh.error;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * @author 海盗狗
 * @version 1.0
 */
@Configuration
@Deprecated
public class MvcErrorViewConfig {

//    异常处理页面
    @Bean
    public HandlerExceptionResolver handlerExceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver =
                new SimpleMappingExceptionResolver();
        exceptionResolver.setDefaultErrorView("/error");
        return exceptionResolver;
    }
}

