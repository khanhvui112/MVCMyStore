package com.sanvui.service;

import com.sanvui.dto.AccountDto;
import com.sanvui.dto.MenuDTO;
import com.sanvui.dto.MessageValidator;
import com.sanvui.model.entity.Employee;
import com.sanvui.model.entity.OrderDetails;
import com.sanvui.model.entity.Orders;
import com.sanvui.repository.EmployeeRepository;
import com.sanvui.utils.EncyptUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public MessageValidator insert(Employee employee){
        boolean username = generateCheck(employee,"username");
        boolean email = generateCheck(employee,"email");
        boolean phone = generateCheck(employee,"phone");
        MessageValidator validator = new MessageValidator();
        if(username){
            validator.setUserName("Tên tài khoản đã có người sử dụng!");
        }
        if(phone){
            validator.setPhone("Số điện thoại đã tồn tại!");
        }
        if(email){
            validator.setEmail("Email đã tồn tại!");
        }
        if(username || email || phone){
            validator.setStatus("false");
            return validator;
        }
        Employee employeeI = employeeRepository.save(employee);

        if(Objects.nonNull(employeeI)){
            return null;
        }
        validator.setStatus("false");
        return validator;

    }

    public Employee getValidEmployee(AccountDto dto) {
        return employeeRepository.findByUserNameAndPassword(dto.getUserName(), EncyptUtil.hashText(dto.getPassword()));
    }

    public Employee findByUserName(String s) {
        return employeeRepository.findByUserName(s);
    }
    public boolean generateCheck(Employee employee, String name){
        if(name.equals("username")){
            return employeeRepository.findByUserName(employee.getUserName()) != null;
        }
        if(name.equals("phone")){
            return employeeRepository.findByPhone(employee.getPhone()) != null;
        }
        if(name.equals("email")){
            return employeeRepository.findByEmail(employee.getEmail()) != null;
        }
        return false;
    }

}