package com.sanvui.repository.custom;

import com.sanvui.model.dto.resp.ColorResponseDto;
import com.sanvui.model.entity.Color;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author: VuiSK
 * @created: 06/12/2021-9:09 PM
 * @mailto: sanvankhanh@gmail.com
 */

public interface CustomizeColorRepository extends CustomizeBaseRepository<ColorResponseDto> {

    @Query(value = "SELECT * FROM color where color_id =:id ", nativeQuery = true)
    Color findColorById(@Param("id") Integer id);
}
