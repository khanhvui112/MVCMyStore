package com.sanvui.controller;

import com.sanvui.model.dto.AccountDto;
import com.sanvui.model.entity.Employee;
import com.sanvui.service.EmployeeServices;
import com.sanvui.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author: VuiSK
 * @created: 11/11/2021-1:29 AM
 * @mailto: sanvankhanh@gmail.com
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @Value("${cookie.access_token.expires}")
    private Integer timeToken;

    @Autowired
    EmployeeServices services;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String login(Model model) {

        model.addAttribute("accountDto", new AccountDto());

        return "login";
    }

    @PostMapping
    public String submitLogin(@RequestParam("username") String username
            , @RequestParam("password") String password
            , HttpServletResponse response){

        Optional<Employee> employee = services.findByUserName(username);
        if(employee.isPresent()
                && passwordEncoder.matches(password,employee.get().getPassword())){
            try {
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                List<GrantedAuthority> grantedAuthorities = employee.get().getEmployeeRoles()
                        .stream().map(r->new SimpleGrantedAuthority(r.getRole().getRoleName()))
                        .collect(Collectors.toList());

                UserDetails userDetails = new User(employee.get().getUserName()
                        , employee.get().getPassword()
                        , grantedAuthorities);

                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);

                Map<String, Object> objectMap = new HashMap<>();
                objectMap.put("userName", userDetails.getUsername());

                List<String> role = userDetails.getAuthorities()
                        .stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());

                objectMap.put("role", role);

                String token = JwtUtil.encode(objectMap);
                Cookie cookie = new Cookie("access_token",token);
                cookie.setMaxAge(timeToken);
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
                return "redirect:/";
            } catch (Exception ignored) {
            }

        }
        return "/login";
    }




}
