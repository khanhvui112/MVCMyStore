package com.sanvui.service;

import com.sanvui.model.dao.Imp.BaseDaoImp;
import com.sanvui.model.entity.FooterDetail;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 17/11/2021-2:39 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class FooterDetailServices {
    public static boolean inserts(List<FooterDetail> footerDetails) {
        BaseDaoImp imp = new BaseDaoImp(FooterDetail.class);
        return imp.insert(footerDetails);
    }
}
