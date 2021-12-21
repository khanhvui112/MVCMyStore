package com.sanvui.controller.api;

import com.sanvui.model.dto.resp.BaseResponseDto;
import com.sanvui.model.entity.ProductDetails;
import com.sanvui.service.ProductDetailService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 01/12/2021-8:19 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
@RequestMapping("/api/v1/product-details")
public class ProductDetailResource {

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping
    public ResponseEntity findAll() {
        List<ProductDetails> productDetails = productDetailService.findAll();
        if (CollectionUtils.isNotEmpty(productDetails)) {
            return ResponseEntity
                    .ok(BaseResponseDto
                            .success("Get ProductDetail success", productDetails));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(BaseResponseDto.error("Get ProductDetail fail. Not found exception", "404"));
    }
}
