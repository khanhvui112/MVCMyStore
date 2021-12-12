package com.sanvui.controller;

import com.sanvui.service.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * @author: VuiSK
 * @created: 04/12/2021-2:55 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Controller
@RequestMapping("")
public class ActiveAccountController {

    @Autowired
    private EmployeeServices employeeServices;

    @Value("${app.key.encrypt}")
    String keyEncrypt;

    @Value("${app.token.time}")
    Integer time;

    @GetMapping("/active-account")
    public String activeAccount(@RequestParam("active-token") String activeToken
            , Model model) {

        String tokenSub = activeToken.replaceAll(" ", "+");

        return employeeServices.activeAccount(tokenSub, model);
    }

    @GetMapping("/resend-token")
    public String resendToken(@RequestParam(value = "active_token", required = false) String token) throws MessagingException, UnsupportedEncodingException {
        return employeeServices.sendTokenAgain(token);
    }
}
