package com.sanvui.repository.custom.impl;

import com.sanvui.model.dto.resp.ManufacturerResponseDto;
import com.sanvui.repository.custom.CustomizeManufacturerRepository;
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
 * @created: 07/12/2021-9:09 AM
 * @mailto: sanvankhanh@gmail.com
 */

public class CustomizeManufacturerRepositoryImpl implements CustomizeManufacturerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public List getAllBase() throws IOException {

        Resource resource = resourceLoader
                .getResource("classpath:sql/manufacturer/select_all_manufacturer.sql");
        String query = FileUtils.asString(resource);

        return entityManager
                .createNativeQuery(query, "manufacturerCustomResultMapping")
                .getResultList();
    }
}
