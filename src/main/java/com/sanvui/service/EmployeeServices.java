package com.sanvui.service;

import com.sanvui.model.dto.AccountDto;
import com.sanvui.model.dto.MessageValidator;
import com.sanvui.model.dto.param.MailParamDto;
import com.sanvui.model.entity.Employee;
import com.sanvui.repository.EmployeeRepository;
import com.sanvui.utils.AES;
import com.sanvui.utils.EncyptUtil;
import com.sanvui.utils.MailParamDtoUtil;
import com.sanvui.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * @author: VuiSK
 * @created: 07/11/2021-12:45 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Service
public class EmployeeServices {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Value("${app.page.size}")
    private Integer pageSize;

    @Value("${app.key.encrypt}")
    private String keyEncrypt;

    @Value("${app.token.time}")
    Integer time;

    @Value("${app.url-local.url}")
    private String url;

    @Autowired
    private EmailService emailService;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public MessageValidator insert(Employee employee) {
        boolean username = generateCheck(employee, "username");
        boolean email = generateCheck(employee, "email");
        boolean phone = generateCheck(employee, "phone");
        MessageValidator validator = new MessageValidator();
        if (username) {
            validator.setUserName("Tên tài khoản đã có người sử dụng!");
        }
        if (phone) {
            validator.setPhone("Số điện thoại đã tồn tại!");
        }
        if (email) {
            validator.setEmail("Email đã tồn tại!");
        }
        if (username || email || phone) {
            validator.setStatus("false");
            return validator;
        }
//        encrypt password
        employee.setPassword(EncyptUtil.passwordEncoder().encode(employee.getPassword()));
        Employee employeeI = employeeRepository.save(employee);

        if (Objects.nonNull(employeeI)) {
            return null;
        }
        validator.setStatus("false");
        return validator;

    }

    //    TODO: active account
    public String activeAccount(String activeToken, Model model) {

        Employee employee = employeeRepository.findByTokenActive(activeToken);
        if (Objects.isNull(employee)) {
            return "/account/_token-not-found";
        }

        String decryptToken = AES.decrypt(activeToken, keyEncrypt);
        LocalDateTime time = LocalDateTime.parse(decryptToken.substring(decryptToken.lastIndexOf("time") + 4));
        LocalDateTime timeNow = LocalDateTime.now();

        if (timeNow.compareTo(time) > 0) {
            model.addAttribute("active_token", activeToken);
            return "/account/_active-fail";
        }
//        TODO: active success
        employee.setActive(true);
        employee.setAccountNonLocked(true);
        employee.setTokenActive(null);
        employeeRepository.save(employee);
        return "/account/_active-success";
    }

    public String sendTokenAgain(String token) throws MessagingException, UnsupportedEncodingException {
//        TODO: Check exists active token
        Employee employee = employeeRepository.findByTokenActive(token);

        /*Check Object != null reset active token
         * Else return page resend fail
         * */
        if (Objects.nonNull(employee)) {

            employee.setTokenActive(TokenUtil.genericActiveToken(employee, keyEncrypt, time));
            employeeRepository.save(employee);

//            TODO: Send mail
            MailParamDto mailParamDto = MailParamDtoUtil.mailParamDto(employee, time, url);

            emailService.sendMail(mailParamDto);

            return "/account/_resend-token";
        }
        return "/account/_token-not-found";
    }

    public Employee getValidEmployee(AccountDto dto) {
        return employeeRepository.findByUserNameAndPassword(dto.getUserName(), EncyptUtil.hashText(dto.getPassword()));
    }

    public Employee findByUserName(String s) {
        return employeeRepository.findByUserName(s);
    }

    public boolean generateCheck(Employee employee, String name) {
        if (name.equals("username")) {
            return employeeRepository.findByUserName(employee.getUserName()) != null;
        }
        if (name.equals("phone")) {
            return employeeRepository.findByPhone(employee.getPhone()) != null;
        }
        if (name.equals("email")) {
            return employeeRepository.findByEmail(employee.getEmail()) != null;
        }
        return false;
    }

    public Page<Employee> getEmployee(Integer page, String sort) {
        Pageable pageable = PageRequest.of(page, pageSize, generateSort(sort));
        return employeeRepository.findAll(pageable);
    }

    private Sort generateSort(String sort) {
        Sort.Order orderByDefault = Sort.Order.desc("empId");


        if (StringUtils.isBlank(sort)) {
            return Sort.by(orderByDefault);
        }
        Sort.Order orderBySortField = sort.startsWith("-") ?
                Sort.Order.desc(sort.substring(1)) : Sort.Order.asc(sort);
        return Sort.by(orderBySortField, orderByDefault);
    }


}