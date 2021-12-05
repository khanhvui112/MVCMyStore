package com.sanvui.controller;

import com.sanvui.model.dto.param.MailParamDto;
import com.sanvui.service.EmailService;
import com.sanvui.service.FileLocalStorageService;
import com.sanvui.service.MenuServices;
import com.sanvui.utils.SessionLoginDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

/**
 * @author: VuiSK
 * @created: 11/11/2021-12:42 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Controller
public class HomeController {

    @Autowired
    EmailService emailService;

    @Autowired
    FileLocalStorageService fileLocalStorageService;

    @GetMapping({"/home","/"})
    public String home(@CookieValue(value=("accessToken"), defaultValue = "") String cookie
            ,HttpSession session) throws MessagingException, UnsupportedEncodingException, MalformedURLException, FileNotFoundException {

        String fileName = "/product/xiaomiviet.vn-dien-thoai-xiaomi-mi-note-10-lite-dien-thoai-xiaomi-mi-note-10-lite-1.jpg";

        return "home";
    }
}
