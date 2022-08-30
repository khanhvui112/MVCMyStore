package com.sanvui.controller.api;

import com.sanvui.model.dto.resp.BaseResponseDto;
import com.sanvui.model.dto.resp.CategoryResponseDto;
import com.sanvui.service.CategoryServices;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 30/11/2021-5:35 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
@RequestMapping("/api/v1/categorys")

public class CategoryResource {

    @Autowired
    private CategoryServices services;

    @GetMapping
    public ResponseEntity findAllCustom() throws IOException {

        List<CategoryResponseDto> categories = services.findAllCustom();
        if (CollectionUtils.isNotEmpty(categories)) {
            return ResponseEntity
                    .ok(BaseResponseDto.success("Get Category success", categories));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(BaseResponseDto
                        .error("Get Category fail. Not found exception", "404"));
    }

}
