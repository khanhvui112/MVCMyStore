package com.sanvui.repository;

import com.sanvui.model.entity.ProductDetails;
import com.sanvui.repository.custom.CustomizeProductDetailRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 01/12/2021-8:20 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetails, Integer>
        , CustomizeProductDetailRepository {

}
