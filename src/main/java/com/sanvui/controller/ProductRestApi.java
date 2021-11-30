package com.sanvui.controller;

import com.sanvui.model.entity.Products;
import com.sanvui.service.ProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 29/11/2021-12:50 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
@RequestMapping("/api/product")
public class ProductRestApi {

    @Autowired
    ProductsServices services;

    @GetMapping("/findAll")
    public List<Products> findAll(){
        return services.findAll();
    }
}
