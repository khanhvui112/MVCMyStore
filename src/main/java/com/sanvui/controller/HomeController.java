package com.sanvui.controller;

import com.sanvui.model.dto.resp.ProductResponseDto;
import com.sanvui.service.FileLocalStorageService;
import com.sanvui.service.ProductsServices;
import com.sanvui.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: VuiSK
 * @created: 11/11/2021-12:42 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Controller
public class HomeController {

    @Autowired
    private ProductsServices productsServices;

    @Autowired
    private FileLocalStorageService fileLocalStorageService;

    @GetMapping({"/home", "/"})
    public String home(HttpSession session) throws IOException {


        String userName = SecurityUtil.getIdCurrentUserLogin();

        if (StringUtils.isNotBlank(userName)) {
            session.setAttribute("userName", userName);
        }

        return "home";
    }
}
