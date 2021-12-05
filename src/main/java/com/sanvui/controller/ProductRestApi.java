package com.sanvui.controller;

import com.sanvui.convert.ProductConvert;
import com.sanvui.model.dto.ProductDto;
import com.sanvui.model.dto.SalaryDTO;
import com.sanvui.model.dto.resp.ProductRespDto;
import com.sanvui.model.entity.Products;
import com.sanvui.service.FileLocalStorageService;
import com.sanvui.service.ProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: VuiSK
 * @created: 29/11/2021-12:50 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductRestApi {

    @Autowired
    ProductsServices services;

    @Autowired
    private FileLocalStorageService fileLocalStorageService;

    @GetMapping
    public List<ProductRespDto> findAllCustom() throws IOException {

        List<ProductRespDto> productRespDtos = services.findAllCustom();

        for (ProductRespDto p : productRespDtos){
            p.setImageLink(fileLocalStorageService.buildUrl(p.getImageLink()));
        }
        return productRespDtos;
    }

    @PreAuthorize("hasAnyAuthority ('ADMIN', 'MANAGER')")
    @PostMapping
    public Products addProduct(
            @RequestParam(value = "image_Link", required = false) MultipartFile file
            ,@RequestParam(value ="productName", required = false) String productName
            ,@RequestParam(value ="price", required = false) String price
            ,@RequestParam(value ="sale_code", required = false) String saleCode
            ,@RequestParam(value ="title", required = false) String title
            ,@RequestParam(value ="description", required = false) String description
            ,@RequestParam(value ="ca_id", required = false) String caId
            ,@RequestParam(value ="color_id", required = false) String colorId
            ,@RequestParam(value ="ma_id", required = false) String maId
            ,@RequestParam(value ="product_detail_id", required = false) String productDetailId
                                       ) throws IOException {
        Products product = new Products();
        product.setProductName(productName);
        product.setPrice(price);
        product.setSale_code(saleCode);
        product.setTitle(title);
        product.setDescription(description);
        product.setCa_id(Integer.parseInt(caId));
        product.setColor_id(Integer.parseInt(colorId));
        product.setMa_id(Integer.parseInt(maId));
        product.setProduct_detail_id(Integer.parseInt(productDetailId));

        if(!file.isEmpty()){
            String imageLink ="/" + fileLocalStorageService.saveFile(file,"product");
            product.setImageLink(imageLink);
        }
        services.save(product);

        return null;
    }




}
