package com.sanvui.model.dao.Imp;

import com.sanvui.dao.Imp.BaseDaoImp;
import com.sanvui.dao.Imp.LoginDTODaoDaoImp;
import com.sanvui.entity.Avatar;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: VuiSK
 * @created: 11/11/2021-10:01 PM
 * @mailto: sanvankhanh@gmail.com
 */

class LoginDTODaoDaoImpTest {

    @Test
    void getLoginDto() {
        LoginDTODaoDaoImp loginDtoDaoDaoImp = new LoginDTODaoDaoImp();
        loginDtoDaoDaoImp.getLoginDto("vui@2310");
    }
    @Test
    void getInsertAvatarTest() {
        BaseDaoImp imp = new BaseDaoImp(Avatar.class);
        Avatar avatar = new Avatar(7,"img.png");
        Avatar avatar1 = new Avatar(1,"vui2310.png");
        List<Avatar> avatarList = new ArrayList<>();
        avatarList.add(avatar1);
        avatarList.add(avatar);
        assertTrue(imp.insert(avatarList));
    }

}