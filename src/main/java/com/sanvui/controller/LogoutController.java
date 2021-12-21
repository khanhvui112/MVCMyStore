package com.sanvui.controller;

import com.sanvui.utils.CookieUtil;
import io.jsonwebtoken.lang.Objects;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;

/**
 * @author: VuiSK
 * @created: 14/11/2021-7:29 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
