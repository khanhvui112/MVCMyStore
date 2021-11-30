package com.sanvui.controller;

import com.sanvui.service.MenuServices;
import com.sanvui.utils.SessionLoginDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @author: VuiSK
 * @created: 11/11/2021-12:42 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Controller
public class HomeController {

    @Autowired
    private MenuServices services;

    @GetMapping({"/home","/"})
    public String home(@CookieValue(value=("accessToken"), defaultValue = "") String cookie
            ,HttpSession session) {
        return "home";
    }
}
