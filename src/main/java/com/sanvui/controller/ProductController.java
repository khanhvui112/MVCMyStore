package com.sanvui.controller;

import com.sanvui.convert.DecimalFormats;
import com.sanvui.model.dto.resp.ProductResponseDto;
import com.sanvui.model.entity.ProductDetails;
import com.sanvui.model.entity.Products;
import com.sanvui.service.FileLocalStorageService;
import com.sanvui.service.ProductDetailService;
import com.sanvui.service.ProductSpecificationServices;
import com.sanvui.service.ProductsServices;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.*;

/**
 * @author: VuiSK
 * @created: 09/12/2021-9:44 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductsServices productsServices;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private ProductSpecificationServices productSpecificationServices;

    @Autowired
    private FileLocalStorageService fileLocalStorageService;

    /*
    * find all product start page
    * sort by field
    * */
    @GetMapping
    public String allProduct(Model model
            ,@RequestParam(value="sort", required = false) String sort
             ,@RequestParam(value="category", required = false) Integer caId
            ,@RequestParam(value="manufacturer", required = false) Integer maId
            ,@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) throws IOException {

        Map<String, String> whereClause = new HashMap<>();

        if(page > 0){
            page = page -1;
        }

        if(Objects.nonNull(maId)){
            whereClause.put("m.ma_id",maId.toString());
        }

        if(Objects.nonNull(caId)){
            whereClause.put("c.ca_id",caId.toString());
        }

        Page<ProductResponseDto> productRespDtos;
        if(StringUtils.isNotBlank(sort) && sort.equals("top20")){
            productRespDtos = productsServices.findTop20CreateDateDesc();
        }else {
            model.addAttribute("sortOld", sort);
            productRespDtos = productsServices.findAllPage(page, whereClause, sort);
        }

        model.addAttribute("totalPages", productRespDtos.getTotalPages());

        model.addAttribute("listProducts", productRespDtos);

        return "product";
    }


   /*
   * find by id
   * */
    @GetMapping("/{id}")
    public String findById(
            @PathVariable Integer id,
            Model model) {

        ProductResponseDto products = productsServices.findByProductId(id);

        model.addAttribute("product",products);

        model.addAttribute("product_specification",productSpecificationServices.findProductSpecificationById(id));

        List<ProductDetails> productDetails = productDetailService.findProductDetailByProductId(id,products.getSale_code());

        model.addAttribute("product_details",productDetails);

        return "product-detail";
    }
}
