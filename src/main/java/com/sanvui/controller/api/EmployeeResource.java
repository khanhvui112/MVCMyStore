package com.sanvui.controller.api;

import com.sanvui.convert.EmployeeConvert;
import com.sanvui.model.dto.EmployeeDto;
import com.sanvui.model.dto.resp.BaseResponseDto;
import com.sanvui.model.entity.Employee;
import com.sanvui.service.EmployeeServices;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: VuiSK
 * @created: 27/11/2021-12:53 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
@RequestMapping("/admin/api-employee")
public class EmployeeResource {

    @Autowired
    private EmployeeServices employeeServices;

    @GetMapping("/find-all")
    public ResponseEntity findAllPage(@RequestParam(value = "page", required = false) Integer page
            , @RequestParam(value = "sort", required = false) String sort) {
        Page<Employee> employees = employeeServices.findAll(page, sort);

        return ResponseEntity
                .ok(BaseResponseDto.success("Get employee success", employees));
    }

    @GetMapping
    public ResponseEntity apiEmployee() {

        List<EmployeeDto> employeeDtos = employeeServices.findAll(1, "-title").stream()
                .map(e -> EmployeeConvert.getInstance().toDTO(e))
                .collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(employeeDtos)) {
            return ResponseEntity
                    .ok(BaseResponseDto.success("Get Employee success", employeeDtos));
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(BaseResponseDto.error("Get Employee fail. Not found exception", "404"));
    }

}
