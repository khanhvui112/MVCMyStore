package com.sanvui.controller;

import com.sanvui.utils.CookieUtil;
import com.sanvui.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author: VuiSK
 * @created: 11/11/2021-12:42 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Controller
public class HomeController {

    @GetMapping({"/home", "/"})
    public String home(HttpSession session
            , HttpServletRequest request
            , HttpServletResponse response) {

//        get all cookies
        Cookie[] cookies = request.getCookies();
//        gte cookie cart
        Cookie cart = CookieUtil.getCookieByName(cookies, "cart");

        String[] carts;
        int quantity = 0;
        /*
        * check cart is blank
        * */
        if(Objects.nonNull(cart)){
            if (StringUtils.isNotBlank(cart.getValue())) {
                carts = cart.getValue().split("-");

                String[] s3 = carts;

                Set<String> list = new LinkedHashSet<>(Arrays.asList(s3));

                quantity = list.size();

            }
        }
        String userName = SecurityUtil.getIdCurrentUserLogin();
        session.setAttribute("quantity", quantity);

        /*
        * end get quantity cart
        * */

        if (StringUtils.isNotBlank(userName)) {
            session.setAttribute("userName", userName);
        }

        return "home";
    }

}
