package com.projectmatching.app.config;

import com.projectmatching.app.domain.user.service.OAuthService;
import com.projectmatching.app.util.AuthTokenProvider;
import com.projectmatching.app.util.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuthService oAuthService;
    private final AuthTokenProvider authTokenProvider;
    private final CorsFilter corsFilter;


    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http)throws Exception {
        http.httpBasic().disable();
        http.csrf().disable().
                headers().frameOptions().disable()
                .and()
                    .addFilter(corsFilter)//항상 해당 필터 통과
                    .formLogin().disable()
                    .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .anyRequest().permitAll()
                .and()
//                .antMatchers("/").hasRole(Role.USER.name())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //토큰 사용하므로 세션 사용 x
                .and()
                .addFilterBefore(new JwtAuthFilter(authTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                    .userInfoEndpoint()
                        .userService(oAuthService);

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
