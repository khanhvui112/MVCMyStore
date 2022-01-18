package com.sanvui.repository.custom.impl;

import com.sanvui.model.entity.Color;
import com.sanvui.repository.custom.CustomizeColorRepository;
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
 * @created: 06/12/2021-9:10 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class CustomizeColorRepositoryImpl implements CustomizeColorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ResourceLoader resourceLoader;


    @Override
    public List getAllBase() throws IOException {
        Resource resource = resourceLoader
                .getResource("classpath:sql/color/select_all_color_custom.sql");
        String query = FileUtils.asString(resource);

        return entityManager
                .createNativeQuery(query, "colorCustomResultMapping")
                .getResultList();
    }

    @Override
    public Color findColorById(Integer id) {
        return null;
    }
}
