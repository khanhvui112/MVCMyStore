package com.sanvui.convert;

import com.sanvui.model.dto.ProductDto;
import com.sanvui.model.dto.resp.ImagesResponseDto;
import com.sanvui.model.dto.resp.ProductDetailRepositoryDto;
import com.sanvui.model.dto.resp.ProductResponseDto;
import com.sanvui.model.entity.Images;
import com.sanvui.model.entity.ProductDetails;
import com.sanvui.model.entity.Products;
import com.sanvui.service.FileLocalStorageService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        p.setImageLink(dto.getImageUrl());
        p.setDescription(dto.getDescription());
        p.setMa_id(dto.getMa_id());
        p.setTitle(dto.getTitle());
        p.setPrice(Double.parseDouble(dto.getPrice()));
        p.setSaleCode(dto.getSale_code());
        return p;
    }

    public ProductResponseDto productToResponseDto(Products dto, FileLocalStorageService fileLocalStorageService) {
        ProductResponseDto p = new ProductResponseDto();
        p.setProductId(dto.getProductId());
        p.setProductName(dto.getProductName());
        p.setCa_id(dto.getCa_id());

        /*
        * Convert Images List to Images list Repository Dto
        * */
        if(!dto.getImagesList().isEmpty()){

            List<ImagesResponseDto> images = new ArrayList<>();
            for(Images t : dto.getImagesList()){
                ImagesResponseDto image = new ImagesResponseDto();

                t.setImageName(fileLocalStorageService.buildUrl(t.getImageName()));

                image.setImageId(t.getImageId());
                image.setImageName(t.getImageName());
                image.setProduct_id(t.getProduct_id());
                image.setImageType(t.getImageType());

                images.add(image);
            }
            p.setImageLink(images);

        }

        p.setDescription(dto.getDescription());
        p.setMa_id(dto.getMa_id());
        p.setTitle(dto.getTitle());
        p.setPrice(dto.getPrice());
        p.setSale_code(dto.getSaleCode());
        return p;
    }
    public List<ProductDetailRepositoryDto> buildProductDetails(Products dto){

        if(!dto.getProductDetails().isEmpty()){

            List<ProductDetailRepositoryDto> productDetailRepositoryDtos = new ArrayList<>();
            for(ProductDetails t : dto.getProductDetails()){
                ProductDetailRepositoryDto detailRepositoryDto = new ProductDetailRepositoryDto();

                detailRepositoryDto.setProductId(t.getProduct_id());
                detailRepositoryDto.setProductDetailId(t.getProductDetailId());
                detailRepositoryDto.setPrice(t.getPrice());
                detailRepositoryDto.setColorId(t.getColor_id());
                detailRepositoryDto.setType(t.getType());
                detailRepositoryDto.setQuantity(t.getQuantity());

                productDetailRepositoryDtos.add(detailRepositoryDto);
            }

            return productDetailRepositoryDtos;
        }
        return  null;
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
        p.setPrice(dto.getPrice());
        p.setSale_code(dto.getSale_code());
        return p;
    }


}
