package com.sanvui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanvui.dto.MessageValidator;
import com.sanvui.model.entity.Employee;
import com.sanvui.service.EmployeeServices;
import com.sanvui.utils.EncyptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @author: VuiSK
 * @created: 28/11/2021-10:24 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
@RequestMapping("/home")
public class SignUpApi {

    @Autowired
    EmployeeServices services;

    @PostMapping(path = "/signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MessageValidator submitSignup(@RequestBody @Valid Employee employee
            , BindingResult bindingResult) throws IOException {
        MessageValidator validator;
        /*
        * check valid
        * if has errors set status fail return
        * */
        if (bindingResult.hasErrors()) {
            validator = new MessageValidator();
            validator.setStatus("false");
            return validator;
        }
        /*
         * Bcypt password
         * */
        employee.setPassword(EncyptUtil.passwordEncoder().encode(employee.getPassword()));

        /*
         * call function insert service
         * if return validator null
         * =>sign up success
         * else sign up fail
         * */

        validator = services.insert(employee);
        if (Objects.nonNull(validator)) {
            return validator;
        }
        validator = new MessageValidator();
        validator.setStatus("true");
        return validator;
    }
}
