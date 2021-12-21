package com.sanvui.repository.custom;

import com.sanvui.model.entity.Images;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 16/12/2021-7:11 PM
 * @mailto: sanvankhanh@gmail.com
 */

public interface CustomizeImagesRepository {

    @Query(value = "SELECT * FROM images WHERE product_id =:id", nativeQuery = true)
    List<Images> findByProductId(@Param("id") Integer productId);
}
