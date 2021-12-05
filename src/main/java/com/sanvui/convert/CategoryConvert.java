package com.sanvui.convert;

import com.sanvui.model.dto.CategoryDto;
import com.sanvui.model.entity.Category;
import com.sanvui.model.entity.Employee;
import com.sanvui.model.entity.EmployeeRole;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 01/12/2021-3:17 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class CategoryConvert {
    private static CategoryConvert instance;

    public static CategoryConvert getInstance() {
        if (instance == null) {
            instance = new CategoryConvert();
        }

        return instance;
    }

    public CategoryDto toDTO(Category c) {
        CategoryDto category = new CategoryDto();
        category.setCaId(c.getCaId());
        category.setCaName(c.getCaName());
        return category;
    }
}
