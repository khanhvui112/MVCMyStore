package com.sanvui.controller;

import com.sanvui.convert.EmployeeConvert;
import com.sanvui.model.dto.EmployeeDto;
import com.sanvui.model.entity.Employee;
import com.sanvui.service.EmployeeServices;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: VuiSK
 * @created: 27/11/2021-12:53 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
@RequestMapping("/admin/api-employee/")
public class EmployeeApi {

    @Autowired
    private EmployeeServices employeeServices;

    @GetMapping("/findAll")
    @ResponseBody
    public ResponseEntity<List<EmployeeDto>> apiEmployee(){

        List<EmployeeDto> employeeDtos = employeeServices.findAll().stream()
                .map(e-> EmployeeConvert.getInstance().toDTO(e))
                .collect(Collectors.toList());

        if(CollectionUtils.isNotEmpty(employeeDtos)){
            return new ResponseEntity(employeeDtos, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find-all")
    @ResponseBody
    public Page<Employee> findAllPage(@RequestParam("page") int page
            ,@RequestParam(value = "sort", required = false) String sort){

        return employeeServices.getEmployee(page,sort);
    }

}
