package com.sanvui.controller;

import com.sanvui.convert.MenuConvert;
import com.sanvui.model.dto.MenuDTO;
import com.sanvui.model.dto.resp.MenuRespDto;
import com.sanvui.service.MenuServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: VuiSK
 * @created: 16/11/2021-1:57 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
@RequestMapping("/api/v1/menu")
public class MenuController extends HttpServlet {

    private final MenuServices menuServices;

    public MenuController(MenuServices services) {
        this.menuServices = services;
    }

    @GetMapping("/find-all")
    public List<MenuRespDto> menuCustom() throws IOException {
        return menuServices.findAllByCustom();
    }
}
