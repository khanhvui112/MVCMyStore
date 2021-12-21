package com.sanvui.utils;

import io.jsonwebtoken.lang.Objects;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author: VuiSK
 * @created: 26/11/2021-2:57 PM
 * @mailto: sanvankhanh@gmail.com
 */

public final class CookieUtil {
    public static Optional<String> getValueByName(Cookie[] cookies, String name) {
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(name))
                .map(Cookie::getValue)
                .findFirst();
    }

    public static Cookie getCookieByName(Cookie[] cookies, String name) {
        if (Objects.isEmpty(cookies)) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    public static Cookie clearCookieByName(Cookie[] cookies, String name) {
        if (Objects.isEmpty(cookies)) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    Cookie cookie = new Cookie("access_token", c.toString());
                    cookie.setMaxAge(0);
                    return cookie;
                }
            }
        }
        return null;
    }
}
