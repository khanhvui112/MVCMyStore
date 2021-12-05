package com.sanvui.convert;

import com.sanvui.model.dto.LoginDTO;
import com.sanvui.model.entity.Employee;

/**
 * @author: VuiSK
 * @created: 23/11/2021-1:58 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class LoginConvert {
    private static LoginConvert instance;

    public static LoginConvert getInstance() {
        if (instance == null) {
            instance = new LoginConvert();
        }

        return instance;
    }

    public LoginDTO toDTO(Employee entity) {
        if(entity==null){
            return null;
        }
        LoginDTO dto = new LoginDTO();
        dto.setUserName(entity.getUserName());
        if (entity.getAvatar() != null) {
            dto.setAvatar(entity.getAvatar().getAvatarName());
        }
        if (entity.getEmployeeRoles() != null) {
            dto.setEmployeeRoles(entity.getEmployeeRoles());
        }
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        return dto;
    }

}
