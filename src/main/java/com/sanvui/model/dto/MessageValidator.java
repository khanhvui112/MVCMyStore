package com.sanvui.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: VuiSK
 * @created: 12/11/2021-10:07 PM
 * @mailto: sanvankhanh@gmail.com
 */

@Getter
@Setter
@AllArgsConstructor

@NoArgsConstructor
public class MessageValidator {

    private String userName;


    private String email;


    private String phone;


    private String password;


    private String rePassword;


    private String dateOfBirth;

    private String gender;


    private String status;


    @Override
    public String toString() {
        return "MessageValidator{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", rePassword='" + rePassword + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
