package com.projectmatching.app.config;

import com.projectmatching.app.util.JwtAuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *  기존 시큐리티 필터 앞 또는 뒤에 하지 않고 따로 필터를 만듦
 *  시큐리티 체인 필터보다 나중에 실행된다
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtAuthFilter> jwtFilter(){
        FilterRegistrationBean<JwtAuthFilter> bean = new FilterRegistrationBean<>();
        bean.addUrlPatterns("/*");
        bean.setOrder(0); //낮은 번호가 필터중에서 가장 먼저 실행
        return bean;

    }
}
