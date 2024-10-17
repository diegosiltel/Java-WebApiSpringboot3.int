package com.interceptors.demo.springboot3_int;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier("loadingTimeInterceptor")
    private HandlerInterceptor timeHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> permitedPaths = new ArrayList<>();
        permitedPaths.add("/api/alfa");
        permitedPaths.add("/api/beta");

        registry.addInterceptor(timeHandlerInterceptor).addPathPatterns(permitedPaths);
    }

}
