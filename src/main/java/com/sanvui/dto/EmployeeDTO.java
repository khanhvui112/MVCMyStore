package com.sanvui.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

/**
 * @author: VuiSK
 * @created: 15/11/2021-1:51 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {
    private int empId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phone;
    private String depName;
    private String roleName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    private String gender;
    private String address;


}
