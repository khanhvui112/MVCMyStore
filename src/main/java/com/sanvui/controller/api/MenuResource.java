package com.sanvui.controller.api;

import com.sanvui.model.dto.resp.BaseResponseDto;
import com.sanvui.model.dto.resp.MenuResponseDto;
import com.sanvui.service.MenuServices;
import com.sanvui.utils.CookieUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: VuiSK
 * @created: 16/11/2021-1:57 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
@RequestMapping("/api/v1/menus")
public class MenuResource {

    private final MenuServices menuServices;

    public MenuResource(MenuServices services) {
        this.menuServices = services;
    }

    @GetMapping
    public ResponseEntity<BaseResponseDto> findAll(
            HttpSession session
            , @CookieValue(value = "cart", defaultValue = "") String cart
            , HttpServletResponse response) throws IOException {

        List<MenuResponseDto> menuResponseDtos = menuServices.findAllByCustom();

        if (CollectionUtils.isNotEmpty(menuResponseDtos)) {
            return ResponseEntity
                    .ok(BaseResponseDto
                            .success("Get data success", menuResponseDtos));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(BaseResponseDto.error("Get data fail. Not found", "404"));
    }
}
