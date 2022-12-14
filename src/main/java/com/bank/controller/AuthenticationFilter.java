package com.bank.controller;

import com.bank.services.CookieService;
import com.bank.services.UserService;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class AuthenticationFilter extends GenericFilterBean {
    private final UserService userService;

    public AuthenticationFilter(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void doFilter(jakarta.servlet.ServletRequest servletRequest, jakarta.servlet.ServletResponse servletResponse, jakarta.servlet.FilterChain filterChain) throws IOException, jakarta.servlet.ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        try {
            CookieService.getSessionId(httpRequest).ifPresent(userService::authenticate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
