package com.sanvui.repository.custom;

import com.sanvui.model.dto.resp.CategoryResponseDto;


import java.io.IOException;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 02/12/2021-3:48 PM
 * @mailto: sanvankhanh@gmail.com
 */

public interface CustomizeCategoryRepository {
    List<CategoryResponseDto> getAllCategoryResp() throws IOException;
}
