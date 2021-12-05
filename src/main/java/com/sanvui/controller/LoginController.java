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


    @GetMapping("/home/login")
    public String login(Model model) {
        model.addAttribute("accountDto", new AccountDto());
        return "login";
    }

    @PostMapping("/home/login")
    public String submitLogin(@ModelAttribute AccountDto accountDto
            , HttpServletResponse response
            , Model model
            , HttpSession session) {
        Employee user = services.findByUserName(accountDto.getUserName());
        boolean checkPass = BCrypt.checkpw(accountDto.getPassword(),user.getPassword());
        if (checkPass) {
            /*
            * Convert Employee to LoginDto
            * */
            LoginDTO dto = LoginConvert.getInstance().toDTO(user);

            /*
            * get secretKey
            * */
            String key = AES.getSecret();
            String accessTokenStr = dto.getUserName()+"em"+dto.getEmail()+"rl"
                    +dto.getEmployeeRoles()+"pw"+dto.getPassword()+"avt"+dto.getAvatar();

            /*
            * encrypt accessTokenStr
            * */
            accessTokenStr = AES.encrypt(accessTokenStr,key);

            /*
            * add cookie save accessToken
            * */
            Cookie cookie = new Cookie("accessToken", accessTokenStr);

            /*
            * set time cookie 7 day
            * */
            cookie.setMaxAge(60*60*24*7);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            response.addCookie(cookie);
            return "redirect:/home";
        }
        session.setAttribute("error","Đăng nhập thất bại.");
        session.setMaxInactiveInterval(5);
        return "login";
    }
}
