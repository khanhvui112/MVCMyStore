package com.sanvui.repository.custom;

import com.sanvui.model.entity.ProductDetails;
import com.sanvui.model.entity.ProductSpecification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomizeProductSpecificationRepository {
    @Query(value = "SELECT * FROM productspecification WHERE product_id =:id", nativeQuery = true)
    ProductSpecification findProductSpecificationByProduct_id(@Param("id") Integer productId);
}
