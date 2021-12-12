package com.sanvui.repository.custom;

import com.sanvui.model.dto.resp.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: VuiSK
 * @created: 05/12/2021-12:44 PM
 * @mailto: sanvankhanh@gmail.com
 */

public interface CustomizeProductRepository {

    List<ProductResponseDto> findAllCustom() throws IOException;

    Page<ProductResponseDto> findPage(Pageable pageable, Map<String, String> whereClause) throws IOException;

    Page<ProductResponseDto> findTop20CreateDateDesc() throws IOException;

}
