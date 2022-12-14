package com.bank.services;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class CookieService {
    public static final String NAME_SESSION_COOKIE = "auth";

    public static void set(HttpServletResponse response,
                           String cookieValue,
                           long age) {
        Cookie cookie = new Cookie(NAME_SESSION_COOKIE, cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge((int) age);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

    public static Optional<String> getSessionId(HttpServletRequest request) {
        return CookieService.getCookieByValue(request, CookieService.NAME_SESSION_COOKIE);
    }

    public static Optional<String> getCookieByValue(HttpServletRequest request, String name) {
        final Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return Optional.empty();
        }
        return Stream.of(cookies).filter(cookie -> cookie.getName().equals(name) && !StringUtils.isEmpty(cookie.getValue())).map(Cookie::getValue).findFirst();
    }
}
