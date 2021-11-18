package com.sanvui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: VuiSK
 * @created: 11/11/2021-9:40 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class LoginDTO {
    private String userName;
    private String password;
    private String role;
    private String avatar;

    @Override
    public String toString() {
        return "LoginDTO{" +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
