package com.sanvui.controller;

import com.sanvui.convert.LoginConvert;
import com.sanvui.model.dto.AccountDto;
import com.sanvui.model.dto.LoginDTO;
import com.sanvui.model.entity.Employee;
import com.sanvui.service.EmployeeServices;
import com.sanvui.utils.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author: VuiSK
 * @created: 11/11/2021-1:29 AM
 * @mailto: sanvankhanh@gmail.com
 */

@Controller
public class LoginController {

    @Autowired
    EmployeeServices services;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("accountDto", new AccountDto());
        return "login";
    }

}
