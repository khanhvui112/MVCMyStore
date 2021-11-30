package com.sanvui.service;

import com.sanvui.model.entity.Products;
import com.sanvui.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 06/11/2021-3:12 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Service
public class ProductsServices {
    @Autowired
    ProductRepository repository;

    public List<Products> findAll(){
        return (List<Products>) repository.findAll();
    }
}
