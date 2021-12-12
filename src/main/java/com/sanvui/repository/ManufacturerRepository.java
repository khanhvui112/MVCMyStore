package com.sanvui.repository;

import com.sanvui.model.entity.Manufacturer;
import com.sanvui.repository.custom.CustomizeManufacturerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: VuiSK
 * @created: 01/12/2021-6:11 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>
        , CustomizeManufacturerRepository {
}
