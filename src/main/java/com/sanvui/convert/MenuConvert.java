package com.sanvui.convert;

import com.sanvui.dto.MenuDTO;
import com.sanvui.entity.Menu;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 16/11/2021-2:59 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class MenuConvert {
    private static MenuConvert instance;

    public static MenuConvert getInstance() {
        if (instance == null) {
            instance = new MenuConvert();
        }

        return instance;
    }

    public MenuDTO toDTO(Menu entity) {

        MenuDTO dto = new MenuDTO();

        dto.setMeId(entity.getMeId());
        dto.setMenuName(entity.getMeName());
        dto.setMenuLink(entity.getMeLink());
        dto.setCreateDate(entity.getCreateDate());
        dto.setEmpId(entity.getEmp_Id());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public static List<MenuDTO> removeByStatus(List<MenuDTO> dtos, int status) {
        for (int i = 0; i < dtos.size(); i++) {
            MenuDTO dto = dtos.get(i);
            if (dto.getStatus() == status) {
                dtos.remove(dto);
                i--;
            }
        }
        return dtos;
    }
}
