package com.sanvui.service;

import com.sanvui.model.dto.resp.MenuResponseDto;
import com.sanvui.model.entity.Menu;
import com.sanvui.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 07/11/2021-11:56 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Service
public class MenuServices {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Cacheable("menus")
    public List<MenuResponseDto> findAllByCustom() throws IOException {
        return menuRepository.getAllMenuResp();
    }
}
