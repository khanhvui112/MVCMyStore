package com.sanvui.service;

import com.sanvui.dao.Imp.EmployeeDaoImp;
import com.sanvui.dto.MessageValidator;
import com.sanvui.entity.Employee;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author: VuiSK
 * @created: 07/11/2021-12:45 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class EmployeeServices {
    private static final EmployeeDaoImp employeeDao = new EmployeeDaoImp();
    public static MessageValidator getMessage(Object o){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> validatorBean = validator.validate(o);
        MessageValidator messages = new MessageValidator();
        if(!validatorBean.isEmpty()){
            for (ConstraintViolation<?> constraintViolation : validatorBean){
                if(constraintViolation.getPropertyPath().toString().equals("email")){
                    messages.setEmail(constraintViolation.getMessage());
                }
                if(constraintViolation.getPropertyPath().toString().equals("phone")){
                    messages.setPhone(constraintViolation.getMessage());
                }
                if(constraintViolation.getPropertyPath().toString().equals("userName")){
                    messages.setUserName(constraintViolation.getMessage());
                }
                if(constraintViolation.getPropertyPath().toString().equals("dateOfBirth")){
                    messages.setDateOfBirth(constraintViolation.getMessage());
                }
                if(constraintViolation.getPropertyPath().toString().equals("password")){
                    messages.setPassword(constraintViolation.getMessage());
                }
                if(constraintViolation.getPropertyPath().toString().equals("gender")){
                    messages.setGender(constraintViolation.getMessage());
                }
            }
            return messages;
        }
        return null;
    }
    public static boolean insertUser(Employee employee){
        EmployeeDaoImp employeeDaoImp = new EmployeeDaoImp();
        return employeeDaoImp.insertUser(employee);
    }
    public static boolean findEmployeeByName(String name){
        return employeeDao.findEmployeeByName(name);
    }
    public static boolean findEmployeeEmail(String email){
        return employeeDao.findEmployeeByEmail(email);
    }
    public static boolean findEmployeePhone(String phone){
        return employeeDao.findEmployeeByPhone(phone);
    }
}
