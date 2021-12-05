package com.sanvui.controller;

import com.sanvui.model.dto.MessageValidator;
import com.sanvui.model.dto.param.MailParamDto;
import com.sanvui.model.entity.Employee;
import com.sanvui.service.EmailService;
import com.sanvui.service.EmployeeServices;
import com.sanvui.utils.EncyptUtil;
import com.sanvui.utils.MailParamDtoUtil;
import com.sanvui.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
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
    private EmployeeServices services;

    @Autowired
    private EmailService emailService;

    @Value("${app.url-local.url}")
    private String url;

    @Value("${app.key.encrypt}")
    String keyEncrypt;

    @Value("${app.token.time}")
    Integer time;

    @CrossOrigin
    @PostMapping(path = "/signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MessageValidator submitSignup(@RequestBody @Valid Employee employee
            , BindingResult bindingResult) throws IOException, MessagingException {
        MessageValidator validator;
//        TODO: Validate
        if (bindingResult.hasErrors()) {
            validator = new MessageValidator();
            validator.setStatus("false");
            return validator;
        }

//        TODO: generic token active
        employee.setTokenActive(TokenUtil.genericActiveToken(employee, keyEncrypt, time));

//       TODO: Save to database
        validator = services.insert(employee);
        if (Objects.nonNull(validator)) {
            return validator;
        }

//        TODO: send Mail active
        MailParamDto mailParamDto = MailParamDtoUtil.mailParamDto(employee,time, url);

        emailService.sendMail(mailParamDto);

        validator = new MessageValidator();
        validator.setStatus("true");
        return validator;
    }
}
