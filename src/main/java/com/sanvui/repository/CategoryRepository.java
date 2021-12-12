package com.sanvui.repository;

import com.sanvui.model.entity.Category;
import com.sanvui.repository.custom.CustomizeCategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 30/11/2021-5:32 PM
 * @mailto: sanvankhanh@gmail.com
 */

public interface CategoryRepository extends JpaRepository<Category, Integer>
        , CustomizeCategoryRepository {

    //    JPQL
    @Query(value = "FROM Category WHERE caName=:name ")
    List<Category> findAll(@Param("name") String name);


    /*
     * SQL Query
     *
     * */
    @Query(value = "SELECT * FROM category ", nativeQuery = true)
    List<Category> findAllSql();

}
