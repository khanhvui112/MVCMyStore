package com.sanvui.repository.custom;

import com.sanvui.model.dto.resp.ProductRespDto;

import java.io.IOException;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 05/12/2021-12:44 PM
 * @mailto: sanvankhanh@gmail.com
 */

public interface CustomizeProductRepository {
    List<ProductRespDto> findAllCustom() throws IOException;
}
