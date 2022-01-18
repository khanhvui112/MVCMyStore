package com.sanvui.controller;

import com.sanvui.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping
    public String showCart(HttpServletRequest request) {
        return "home";
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> addCart(
            HttpSession session
            , HttpServletRequest request
            , @PathVariable("id") Integer id
            , HttpServletResponse response) {

        Cookie[] cookies =request.getCookies();

        Cookie cookie = CookieUtil.getCookieByName(cookies,"cart");

        if(Objects.isNull(cookie)){
            cookie = new Cookie("cart","");
        }
        String cart = cookie.getValue();

        if (Objects.nonNull(id)) {
            cart = cart + id + "-";
        }

        String[] carts;
        int quantity = 0;
        if (StringUtils.isNotBlank(cart)) {
            carts = cart.split("-");

            String[] s3 = carts;

            Set<String> list = new LinkedHashSet<>(Arrays.asList(s3));

            quantity = list.size();

        }

        cookie.setValue(cart);
        cookie.setMaxAge(60*60*24*7);
        cookie.setPath("/");

        session.setAttribute("quantity", quantity);

/*        cookie.setMaxAge(60 * 60 * 7 * 24);*/
        response.addCookie(cookie);

        return new ResponseEntity<String>(String.valueOf(quantity), HttpStatus.OK);
    }
}
