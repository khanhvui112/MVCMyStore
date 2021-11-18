package com.sanvui.convert;

import com.sanvui.entity.Footer;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 17/11/2021-3:00 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class FooterConvert {
    private static FooterConvert instance;

    public static FooterConvert getInstance() {
        if (instance == null) {
            instance = new FooterConvert();
        }

        return instance;
    }

    public Footer toDTO(Footer entity) {

        Footer dto = new Footer();

        dto.setFooterId(entity.getFooterId());
        dto.setFooterName(entity.getFooterName());
        dto.setStatus(entity.getStatus());
        dto.setCreate_Date(entity.getCreate_Date());
        if(entity.getFooterDetails() != null){
            dto.setFooterDetails(entity.getFooterDetails());
        }
        return dto;
    }

    public static List<Footer> removeByStatus(List<Footer> dtos, int status) {
        for (int i = 0; i < dtos.size(); i++) {
            Footer dto = dtos.get(i);
            if (dto.getStatus() == status) {
                dtos.remove(dto);
                i--;
            }
        }
        return dtos;
    }
}
