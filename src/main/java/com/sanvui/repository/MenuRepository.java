package com.sanvui.repository;

import com.sanvui.model.entity.Menu;
import com.sanvui.repository.custom.CustomizeMenuRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: VuiSK
 * @created: 21/11/2021-10:51 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>
        , CustomizeMenuRepository {

}
