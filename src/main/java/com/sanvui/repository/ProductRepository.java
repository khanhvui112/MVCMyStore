package com.sanvui.repository;

import com.sanvui.model.dto.resp.ProductResponseDto;
import com.sanvui.model.entity.Products;
import com.sanvui.repository.custom.CustomizeProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author: VuiSK
 * @created: 29/11/2021-12:53 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Repository
public interface ProductRepository extends BaseRepository<Products, Integer>
        , CustomizeProductRepository {
}
