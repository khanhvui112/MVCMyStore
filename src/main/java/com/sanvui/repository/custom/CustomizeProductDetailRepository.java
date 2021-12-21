package com.sanvui.repository.custom;

import com.sanvui.model.entity.ProductDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 18/12/2021-5:28 PM
 * @mailto: sanvankhanh@gmail.com
 */

public interface CustomizeProductDetailRepository {
    @Query(value = "SELECT * FROM productdetails WHERE product_id =:id", nativeQuery = true)
    List<ProductDetails> findByProductId(@Param("id") Integer productId);
}
