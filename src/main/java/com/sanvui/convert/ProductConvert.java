package com.sanvui.convert;

import com.sanvui.model.dto.ProductDto;
import com.sanvui.model.dto.resp.ProductRespDto;
import com.sanvui.model.entity.Employee;
import com.sanvui.model.entity.EmployeeRole;
import com.sanvui.model.entity.Products;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 01/12/2021-9:43 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class ProductConvert {
    private static ProductConvert instance;

    public static ProductConvert getInstance() {
        if (instance == null) {
            instance = new ProductConvert();
        }

        return instance;
    }

    public Products toProduct(ProductDto dto) {
        Products p = new Products();
        p.setProductName(dto.getProductName());
        p.setCa_id(dto.getCa_id());
        p.setColor_id(dto.getColor_id());
        p.setImageLink(dto.getImageUrl());
        p.setDescription(dto.getDescription());
        p.setMa_id(dto.getMa_id());
        p.setTitle(dto.getTitle());
        p.setProduct_detail_id(dto.getProduct_detail_id());
        p.setPrice(dto.getPrice());
        p.setSale_code(dto.getSale_code());
        return p;
    }
    public ProductRespDto toProduct(ProductRespDto dto, String local) {
        ProductRespDto p = new ProductRespDto();
        p.setProductName(dto.getProductName());
        p.setCa_id(dto.getCa_id());
        p.setColor_id(dto.getColor_id());
        p.setImageLink(dto.getImageLink());
        p.setDescription(dto.getDescription());
        p.setMa_id(dto.getMa_id());
        p.setTitle(dto.getTitle());
        p.setProduct_detail_id(dto.getProduct_detail_id());
        p.setPrice(dto.getPrice());
        p.setSale_code(dto.getSale_code());
        return p;
    }
}
