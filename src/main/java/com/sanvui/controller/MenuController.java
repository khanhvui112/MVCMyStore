package com.sanvui.controller;

import com.sanvui.convert.MenuConvert;
import com.sanvui.dto.MenuDTO;
import com.sanvui.service.MenuServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: VuiSK
 * @created: 16/11/2021-1:57 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
public class MenuController extends HttpServlet {

    private final MenuServices services;

    public MenuController(MenuServices services) {
        this.services = services;
    }

    @GetMapping("/menu-all")
    public List<MenuDTO> menu(){
        List<MenuDTO> menus = services.findAll().stream().map(
                m-> MenuConvert.getInstance().toDTO(m)).collect(Collectors.toList());
        menus = MenuConvert.removeByStatus(menus,0);
        return menus;
    }
}
