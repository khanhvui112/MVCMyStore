package com.sanvui.controller;

import com.sanvui.convert.EmployeeConvert;
import com.sanvui.dto.EmployeeDTO;
import com.sanvui.model.entity.Employee;
import com.sanvui.service.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutorService;
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
    private EmployeeServices service;

    @GetMapping("/findAll")
    public List<EmployeeDTO> apiEmployee(){
        return service.findAll().stream()
                .map(e-> EmployeeConvert.getInstance().toDTO(e))
                .collect(Collectors.toList());
    }

}
