package com.projectmatching.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); //내 서버가 응답을 할 때, json을 자바스크립트에서 처리할 수 있도록 설정
        config.addAllowedOrigin("*"); //모든 ip에 응답을 허용함
        config.addAllowedHeader("*"); // 모든 header에 응답을 허용하겠다
        config.addAllowedMethod("*"); //모든 http 요청에 응답하겠다
        source.registerCorsConfiguration("/api/**",config); // api 로시작하는 모든 요청이 config설정을 따르도록함
        return new CorsFilter(source);


    }
}
