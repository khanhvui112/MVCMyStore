package com.sanvui.service;

import com.sanvui.model.dto.resp.ProductResponseDto;
import com.sanvui.model.entity.ProductDetails;
import com.sanvui.repository.ProductDetailRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    @Autowired
    private ColorServices colorServices;

    @Cacheable("productDetails")
    public List<ProductDetails> findAll() {
        return detailRepository.findAll(Sort.by("productDetailId").ascending());
    }

    public ProductDetails findProductDetailById(Integer productDetailId) {
        return (detailRepository.findById(productDetailId)).isPresent()
                ? detailRepository.getById(productDetailId) : null;
    }

    public List<ProductDetails> findProductDetailByProductId(Integer productDetailId, String saleCode) {
        List<ProductDetails> productDetails = detailRepository.findByProductId(productDetailId);
        for (ProductDetails p : productDetails) {
            p.setColor(colorServices.findById(p.getColor_id()));
            p.setPriceSales(buildPriceSale(p.getPrice(), saleCode));
        }
        return productDetails;
    }

    private Double buildPriceSale(Double price, String saleCode) {
        if(StringUtils.isNotBlank(saleCode)){
            int saleCodeInt = saleCode.indexOf("%");
            double saleCodeDouble = 0;
            if(saleCodeInt != -1){
                saleCodeDouble = Integer
                        .parseInt((saleCode).replaceAll("%",""));

                saleCodeDouble = (100-saleCodeDouble)/100;

                return price * saleCodeDouble;
            }
            saleCodeDouble = Integer
                    .parseInt((saleCode).replaceAll("K",""));

            return price - (saleCodeDouble*1000);
        }
        return null;
    }


}
