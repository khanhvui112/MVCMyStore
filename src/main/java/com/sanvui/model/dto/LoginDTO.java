package com.sanvui.model.dto;

import com.sanvui.model.entity.EmployeeRole;
import lombok.*;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 11/11/2021-9:40 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {
    private String userName;
    private String email;
    private String password;
    private List<EmployeeRole> employeeRoles;
    private String avatar;

    @Override
    public String toString() {
        return "LoginDTO{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + employeeRoles + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
