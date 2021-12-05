package com.sanvui.service;

import com.sanvui.model.entity.ProductDetail;
import com.sanvui.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 07/11/2021-11:12 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Service
public class ProductDetailService {

    @Autowired
    private ProductDetailRepository detailRepository;

    public List<ProductDetail> findAll(){
        return detailRepository.findAll(Sort.by("productDetailId").ascending());
    }
}
