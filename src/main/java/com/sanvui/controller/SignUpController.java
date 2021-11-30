package com.sanvui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanvui.dto.MenuDTO;
import com.sanvui.dto.MessageValidator;
import com.sanvui.model.entity.Employee;
import com.sanvui.response.EmployeeResponse;
import com.sanvui.service.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author: VuiSK
 * @created: 11/11/2021-1:29 AM
 * @mailto: sanvankhanh@gmail.com
 */

@Controller
public class SignUpController {


    @Autowired
    EmployeeServices services;


    @GetMapping("/home/signup")
    public String login() {
        return "registration";
    }

}
