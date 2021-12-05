package com.sanvui.repository;

import com.sanvui.model.entity.Products;
import com.sanvui.repository.custom.CustomizeProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author: VuiSK
 * @created: 29/11/2021-12:53 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Repository
public interface ProductRepository extends JpaRepository<Products, Integer>
        , CustomizeProductRepository {
}
