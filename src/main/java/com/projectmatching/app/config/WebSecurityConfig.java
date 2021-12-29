package com.projectmatching.app.config;

import com.projectmatching.app.domain.user.Role;
import com.projectmatching.app.domain.user.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final OAuthService oAuthService;

    public WebSecurityConfig(OAuthService oAuthService){
        this.oAuthService = oAuthService;
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception {
        http.csrf().disable().
                headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers("/").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(oAuthService);
    }

}
