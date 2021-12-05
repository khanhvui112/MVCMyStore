package com.sanvui.repository.custom.impl;

import com.sanvui.model.dto.CategoryDto;
import com.sanvui.model.dto.resp.CategoryRespDto;
import com.sanvui.repository.custom.CustomizeCategoryRepository;
import com.sanvui.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 02/12/2021-3:50 PM
 * @mailto: sanvankhanh@gmail.com
 */
public class CustomizeCategoryRepositoryImpl implements CustomizeCategoryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public List getAllCategoryResp() throws IOException {
        Resource resource = resourceLoader.
                getResource("classpath:sql/category/select_all_category.sql");

        String query = FileUtils.asString(resource);

        return entityManager.
                createNativeQuery(query, "categoryCustomResultMapping").
                getResultList();
    }

}