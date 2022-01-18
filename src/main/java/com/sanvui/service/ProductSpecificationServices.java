package com.sanvui.service;

import com.sanvui.model.entity.ProductSpecification;
import com.sanvui.repository.ProductSpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductSpecificationServices {

    @Autowired
    private ProductSpecificationRepository productSpecificationRepository;

    public ProductSpecification findProductSpecificationById(int id){

        return productSpecificationRepository.findProductSpecificationByProduct_id(id);

    }
}
