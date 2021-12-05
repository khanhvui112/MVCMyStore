package com.sanvui.repository;

import com.sanvui.model.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: VuiSK
 * @created: 01/12/2021-5:48 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
}
