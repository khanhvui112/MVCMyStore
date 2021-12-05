package com.sanvui.service;

import com.sanvui.model.dto.CategoryDto;
import com.sanvui.model.dto.resp.CategoryRespDto;
import com.sanvui.model.entity.Category;
import com.sanvui.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 06/11/2021-1:52 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Service
public class CategoryServices {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }

    public List<CategoryRespDto> findAllCustom() throws IOException {
        return repository.getAllCategoryResp();
    }
}
