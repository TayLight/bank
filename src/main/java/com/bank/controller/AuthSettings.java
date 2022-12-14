package com.bank.controller;

import com.bank.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;



@Configuration
@Order(1)
@EnableWebSecurity
public class AuthSettings extends WebSecurityConfigurerAdapter {
    private  UserService userService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.mvcMatcher("/api/**")
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .rememberMe().disable()
                .formLogin().disable()
                .logout().disable()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/auth/*").permitAll()
                .anyRequest().authenticated();
        http.headers().contentTypeOptions().disable();
    }

    private AuthenticationFilter getAuthenticationFilter(){
        return new AuthenticationFilter(this.userService);
    }
}
