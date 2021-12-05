package com.sanvui.repository.custom.impl;

import com.sanvui.model.dto.resp.ProductRespDto;
import com.sanvui.repository.custom.CustomizeProductRepository;
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
 * @created: 05/12/2021-12:54 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class CustomizeProductRepositoryImpl implements CustomizeProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public List findAllCustom() throws IOException {
        Resource resource = resourceLoader
                .getResource("classpath:sql/product/select_all_product.sql");

        String query = FileUtils.asString(resource);

        return entityManager
                .createNativeQuery(query, "productCustomResultMapping")
                .getResultList();
    }
}
