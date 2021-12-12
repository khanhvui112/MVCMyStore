package com.sanvui.controller;

import com.sanvui.service.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author: VuiSK
 * @created: 11/11/2021-1:29 AM
 * @mailto: sanvankhanh@gmail.com
 */

@Controller
public class SignUpController {


    @Autowired
    EmployeeServices services;
    
    @GetMapping("/signup")
    public String login() {
        return "registration";
    }

}
