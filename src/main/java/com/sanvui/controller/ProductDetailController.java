package com.sanvui.controller;

import com.sanvui.model.entity.ProductDetail;
import com.sanvui.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/product-detail")
public class ProductDetailController {

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("/find-all")
    public List<ProductDetail> findAll(){
        return productDetailService.findAll();
    }
}
