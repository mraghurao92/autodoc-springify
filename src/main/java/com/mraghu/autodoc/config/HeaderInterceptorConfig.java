package com.mraghu.autodoc.config;

import com.mraghu.autodoc.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HeaderInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor());
    }

    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }
}