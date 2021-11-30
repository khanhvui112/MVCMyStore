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
 * @created: 11/11/2021-8:49 PM
 * @mailto: sanvankhanh@gmail.com
 */

@Controller
public class DashboardController extends HttpServlet {

    @GetMapping({"/admin-dashboard"})
    public String dashBoard(){
        return "dashboard";
    }
}
