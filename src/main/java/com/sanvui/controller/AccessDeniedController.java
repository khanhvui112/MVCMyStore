package com.sanvui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: VuiSK
 * @created: 19/11/2021-6:22 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Controller
public class AccessDeniedController {

    @GetMapping("/accessDenied")
    public String error() {
        return "/error/error_access_denied";
    }
}
