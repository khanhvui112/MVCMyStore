package com.sanvui.controller;

import com.sanvui.utils.CookieUtil;
import com.sanvui.utils.SessionLoginDtoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.*;
import java.util.Objects;

/**
 * @author: VuiSK
 * @created: 14/11/2021-7:29 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Controller
public class LogoutController{
    @GetMapping("/home/logout")
    public String logout(HttpSession session
            , HttpServletRequest request
            , HttpServletResponse response){
        return "redirect:/home/login";
    }
}
