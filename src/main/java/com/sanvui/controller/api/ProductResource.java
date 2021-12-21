package com.sanvui.controller.api;

import com.sanvui.model.dto.resp.BaseResponseDto;
import com.sanvui.model.dto.resp.ImagesResponseDto;
import com.sanvui.model.dto.resp.ProductResponseDto;
import com.sanvui.model.entity.Products;
import com.sanvui.service.FileLocalStorageService;
import com.sanvui.service.ProductsServices;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author: VuiSK
 * @created: 29/11/2021-12:50 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductResource {

    @Autowired
    ProductsServices services;

    @Autowired
    private FileLocalStorageService fileLocalStorageService;

    @GetMapping("/search")
    public ResponseEntity findAllByName(@RequestParam(name = "name", required = false) String name) {
        return ResponseEntity
                .ok(BaseResponseDto
                        .success("Find product by name success", services.findByNameLike("%" + name + "%")));
    }

    @GetMapping("/page")
    public ResponseEntity findAllPage() throws IOException {
        return ResponseEntity
                .ok(BaseResponseDto
                        .success("Find product by name success", services.findAllPage(0, null, null)));
    }

    @GetMapping
    public ResponseEntity findAllCustom() throws IOException {

        List<ProductResponseDto> productRespDtos = services.findAllCustom();

        if (CollectionUtils.isNotEmpty(productRespDtos)) {
            return ResponseEntity
                    .ok(BaseResponseDto
                            .success("Get Product success", productRespDtos));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(BaseResponseDto
                        .error("Get Products fail. Not found exception", "404"));
    }

    @PreAuthorize("hasAnyAuthority ('ADMIN', 'MANAGER')")
    @PostMapping
    @CacheEvict(value = "products", allEntries = true)
    public ResponseEntity createProduct(
            @RequestParam(value = "image_Link", required = false) MultipartFile file
            , @RequestParam(value = "productName", required = false) String productName
            , @RequestParam(value = "price", required = false) Double price
            , @RequestParam(value = "sale_code", required = false) String saleCode
            , @RequestParam(value = "title", required = false) String title
            , @RequestParam(value = "description", required = false) String description
            , @RequestParam(value = "ca_id", required = false) Integer caId
            , @RequestParam(value = "color_id", required = false) Integer colorId
            , @RequestParam(value = "ma_id", required = false) Integer maId
            , @RequestParam(value = "product_detail_id", required = false) Integer productDetailId

    ) throws IOException {

        Products product = new Products();

        product.setProductName(productName);
        product.setPrice(price);
        product.setSaleCode(saleCode);
        product.setTitle(title);
        product.setDescription(description);
        product.setCa_id(caId);
        product.setMa_id(maId);

        if (file != null) {
            String imageLink = "/" + fileLocalStorageService.saveFile(file, "product");
            product.setImageLink(imageLink);
        }
        if (Objects.nonNull(services.save(product))) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponseDto.error("Create product fail.", "VALID_FAIL"));
    }


}
