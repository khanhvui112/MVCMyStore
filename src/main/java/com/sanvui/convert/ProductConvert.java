package com.sanvui.convert;

import com.sanvui.model.dto.ProductDto;
import com.sanvui.model.dto.resp.ProductResponseDto;
import com.sanvui.model.entity.Products;
import com.sanvui.service.FileLocalStorageService;
import org.apache.commons.lang3.StringUtils;

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
        p.setPrice(Double.parseDouble(dto.getPrice()));
        p.setSaleCode(dto.getSale_code());
        return p;
    }

    public ProductResponseDto productToResponseDto(Products dto, FileLocalStorageService fileLocalStorageService) {
        ProductResponseDto p = new ProductResponseDto();
        p.setProductName(dto.getProductName());
        p.setCa_id(dto.getCa_id());
        p.setColor_id(dto.getColor_id());
        p.setImageLink(fileLocalStorageService.buildUrl(dto.getImageLink()));
        p.setDescription(dto.getDescription());
        p.setMa_id(dto.getMa_id());
        p.setTitle(dto.getTitle());
        p.setProduct_detail_id(dto.getProduct_detail_id());
        p.setPrice(String.valueOf(dto.getPrice()));
        p.setSale_code(dto.getSaleCode());
        return p;
    }

    public ProductResponseDto toProduct(ProductResponseDto dto) {
        ProductResponseDto p = new ProductResponseDto();
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
