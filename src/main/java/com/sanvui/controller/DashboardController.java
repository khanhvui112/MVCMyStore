package com.sanvui.controller;

import com.sanvui.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: VuiSK
 * @created: 11/11/2021-8:49 PM
 * @mailto: sanvankhanh@gmail.com
 */

@Controller
public class DashboardController {

    @GetMapping({"/admin-dashboard"})
    public String dashBoard(HttpSession session) {

        String userName = SecurityUtil.getIdCurrentUserLogin();

        if (StringUtils.isNotBlank(userName)) {
            session.setAttribute("userName", userName);
        }

        return "dashboard";
    }
}
