package com.sanvui.service;

import com.sanvui.dao.Imp.BaseDaoImp;
import com.sanvui.entity.Menu;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 07/11/2021-11:56 AM
 * @mailto: sanvankhanh@gmail.com
 */

public class MenuServices {
    public static List<Menu> findAll(){
        BaseDaoImp imp = new BaseDaoImp(Menu.class);
        return imp.findAll();
    }
}
