package com.sanvui.convert;

import com.sanvui.dto.EmployeeDTO;
import com.sanvui.entity.Employee;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 15/11/2021-2:06 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class EmployeeConvert {
    private static EmployeeConvert instance;

    public static EmployeeConvert getInstance() {
        if (instance == null) {
            instance = new EmployeeConvert();
        }

        return instance;
    }

    public EmployeeDTO toDTO(Employee entity) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmpId(entity.getEmpId());
        if (entity.getDepartment() != null) {
            dto.setDepName(entity.getDepartment().getDepName());
        }
        if (entity.getRole() != null) {
            dto.setRoleName(entity.getRole().getRoleName());
        }
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setUserName(entity.getUserName());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setGender(entity.getGender());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setAddress(entity.getAddress());
        return dto;
    }

    public static List<EmployeeDTO> removeUser(List<EmployeeDTO> dtos) {
        for (int i = 0; i < dtos.size(); i++) {
            EmployeeDTO dto = dtos.get(i);
            if (dto.getDepName() == null
                    || dto.getDepName().equalsIgnoreCase("Người dùng")
                    || dto.getDepName().equalsIgnoreCase("user")) {
                dtos.remove(dto);
                i--;
            }
        }
        return dtos;
    }
}
