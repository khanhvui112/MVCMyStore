package com.sanvui.dao;

import com.sanvui.dto.LoginDTO;

/**
 * @author: VuiSK
 * @created: 11/11/2021-9:46 PM
 * @mailto: sanvankhanh@gmail.com
 */

public interface LoginDTODao {
    LoginDTO getLoginDto(String userName);
}
