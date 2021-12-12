package com.sanvui.service;

import com.sanvui.convert.DecimalFormats;
import com.sanvui.convert.ProductConvert;
import com.sanvui.model.dto.resp.ProductResponseDto;
import com.sanvui.model.entity.Products;
import com.sanvui.repository.ProductRepository;
import com.sanvui.utils.SortUtil;
import com.sanvui.utils.ValidatorBean;
import com.sanvui.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: VuiSK
 * @created: 06/11/2021-3:12 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Service
public class ProductsServices {


    /*
    * value page size products
    * */
    @Value("${app.page.products.size}")
    private Integer pageProductsSize;

    /*
    * value page size10
    * */
    @Value("${app.page.products.size10}")
    private Integer pageProductsSize10;

    @Autowired
    ProductRepository repository;

    @Autowired
    private FileLocalStorageService fileLocalStorageService;

    /*
    * Find product have name like
    * param name query
    * return List ProductResponseDto
    * */
    public List<ProductResponseDto> findByNameLike(String name) {

        return repository.findByProductNameLike(name)
                .stream()
                .map(p -> ProductConvert.getInstance().productToResponseDto(p, fileLocalStorageService))
                .collect(Collectors.toList());
    }

    public List<Products> findAll() {
        return repository.findAll();
    }

//    TODO: Find top product sort by productId desc page size:10
    public Page<ProductResponseDto> findTopProductsPage(Integer page) throws IOException {

        List<String> sort = new ArrayList<>();
        sort.add("product_name");
        sort.add("-title");

        Pageable pageable = PageRequest.of(page
                , pageProductsSize10
                , SortUtil.generateSort(sort,"-product_id"));

        return repository.findPage(pageable, null);

    }

    /*
    * find All product by repository custom
    * param integer page , Map where clause
    * return Page ProductResponseDto
    * */
    public Page<ProductResponseDto> findAllPage(Integer page, Map<String, String> whereClause) throws IOException {

        List<String> sort = new ArrayList<>();
        sort.add("-price");

        Pageable pageable = PageRequest
                .of(page, pageProductsSize
                        , SortUtil.generateSort(sort,"-product_id"));

        Page<ProductResponseDto> productRespDtos = repository.findPage(pageable, whereClause);

        for (ProductResponseDto p : productRespDtos){
            p.setImageLink(fileLocalStorageService.buildUrl(p.getImageLink()));
            p.setPrice(DecimalFormats.decimals(Double.parseDouble(p.getPrice())));
        }

        return productRespDtos;
    }

    /*
    * Select top 20 product
    * order by create desc
    * return Page<ProductResponseDto>
    */
    public Page<ProductResponseDto> findTop20CreateDateDesc() throws IOException {
        return repository.findTop20CreateDateDesc();
    }

    public List<ProductResponseDto> findAllCustom() throws IOException {
        return repository.findAllCustom();
    }


    /*
    * insert new Product to database
    * param Object product
    * if success return Object product
    * else return null
    * */
    public Products save(Products product) {
        ValidatorBean validatorBean = ValidatorUtils.validatorBean(product);
        if (!validatorBean.isSuccess()) {
            return null;
        }
        return repository.save(product);
    }
}
