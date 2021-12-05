package com.sanvui.model.dao;

import com.sanvui.model.dto.SalaryDTO;
import com.sanvui.model.entity.Employee;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 07/11/2021-12:44 PM
 * @mailto: sanvankhanh@gmail.com
 */

public interface EmployeeDao {
    List<SalaryDTO> salaryDtoList();

    List<Employee> findAll();

    List<Employee> employeeList();

    boolean insertUser(Employee employeeList);

    boolean findEmployeeByName(String username);

    boolean findEmployeeByEmail(String email);

    boolean findEmployeeByPhone(String phone);
}
