package com.sanvui.repository;

import com.sanvui.model.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: VuiSK
 * @created: 01/12/2021-8:20 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
}
