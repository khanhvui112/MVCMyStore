package com.sanvui.repository;

import com.sanvui.model.entity.ProductSpecification;
import com.sanvui.repository.custom.CustomizeProductSpecificationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSpecificationRepository extends JpaRepository<ProductSpecification, Integer>
        , CustomizeProductSpecificationRepository {
}
