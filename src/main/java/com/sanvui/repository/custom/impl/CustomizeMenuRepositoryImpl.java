package com.sanvui.repository.custom.impl;

import com.sanvui.repository.custom.CustomizeMenuRepository;
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
 * @created: 05/12/2021-11:56 AM
 * @mailto: sanvankhanh@gmail.com
 */

public class CustomizeMenuRepositoryImpl implements CustomizeMenuRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public List getAllMenuResp() throws IOException {
        Resource resource = resourceLoader
                .getResource("classpath:sql/menu/select_all_menu.sql");

        String query = FileUtils.asString(resource);

        return entityManager
                .createNativeQuery(query, "menuCustomResultMapping")
                .getResultList();
    }
}
