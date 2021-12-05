package com.sanvui.controller;

import com.sanvui.model.dto.resp.CategoryRespDto;
import com.sanvui.model.entity.Category;
import com.sanvui.service.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryServices services;

    @GetMapping("/find-all")
    public List<CategoryRespDto> findAllCustom() throws IOException {
        return services.findAllCustom();
    }

}
