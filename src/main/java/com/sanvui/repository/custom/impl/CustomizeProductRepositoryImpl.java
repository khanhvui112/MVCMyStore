package com.sanvui.repository.custom.impl;

import com.sanvui.model.dto.resp.ProductResponseDto;
import com.sanvui.repository.custom.CustomizeProductRepository;
import com.sanvui.utils.FileUtils;
import org.apache.commons.collections4.MapUtils;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public Page<ProductResponseDto> findPage(Pageable pageable, Map<String, String> whereClause) throws IOException {

        Resource resource = resourceLoader
                .getResource("classpath:sql/product/select_all_page.sql");

        Resource resourceCount = resourceLoader
                .getResource("classpath:sql/product/select_count.sql");

        String where= " ";

        if(MapUtils.isNotEmpty(whereClause)){
            where = getWhereClause(whereClause) + " and ps.type='Default' ";
        }else {
            where = " where ps.type='Default' ";
        }

        String query = FileUtils.asString(resource);

        String queryCount = FileUtils.asString(resourceCount);

        queryCount = queryCount.replace("#whereClause",where);

        Integer totalPages = (Integer) entityManager.createNativeQuery(queryCount).getSingleResult();


        String sort = "order by ";

        List<String> s = pageable.getSort()
                .stream()
                .map(t -> "p." + t.getProperty() + " " + t.getDirection().name())
                .collect(Collectors.toList());

        sort += String.join(", ", s);

        query = query.replace("#whereClause", where);

        query = query.replace("#orderByClause", sort);

        Map<String, Object> objectMap = new HashMap<>();

        objectMap.put("startItem", pageable.getPageNumber() * pageable.getPageSize());

        objectMap.put("itemPage", pageable.getPageSize());

        List productRespDtos = entityManager.createNativeQuery(query
                , "productCustomResultMapping")
                .unwrap(NativeQuery.class)
                .setProperties(objectMap).getResultList();

        return new PageImpl<>(productRespDtos, pageable, totalPages);

    }

    /*
    * Select top 20 product order by create date desc
    * param numberPage and name order
    * */
    @Override
    public Page<ProductResponseDto> findTop20CreateDateDesc() throws IOException {
        Resource resource = resourceLoader
                .getResource("classpath:sql/product/select_top20_products.sql");

        String query = FileUtils.asString(resource);

        List list =  entityManager.createNativeQuery(query,"productCustomResultMapping")
                .getResultList();

        Pageable pageable = PageRequest.of(0,20);

        return new PageImpl<>(list,pageable, 20);
    }

    public String getWhereClause(Map<String, String> whereClause) {
        int count = 0;
        String join = "";
        String where="";
        for (Map.Entry<String, String> key : whereClause.entrySet()){

            if(key.getKey().startsWith("m")){
                join = join + " INNER JOIN manufacturer m on m.ma_id = p.ma_id ";
            }
            if(key.getKey().startsWith("c")){
                join = join + " INNER JOIN category c on c.ca_id = p.ca_id ";
            }

            if(count > 0){
                where = where + " and ";
            }
            count++;

            where = where + key.getKey() +" = "+key.getValue();

        }
        return join +" where "+ where;
    }

}
