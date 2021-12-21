package com.sanvui.repository;

import com.sanvui.model.entity.Images;
import com.sanvui.repository.custom.CustomizeImagesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 16/12/2021-5:54 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Repository
public interface ImagesRepository extends JpaRepository<Images, Integer>
        , CustomizeImagesRepository {
}
