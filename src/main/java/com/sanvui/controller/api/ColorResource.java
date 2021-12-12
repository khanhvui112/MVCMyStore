package com.sanvui.controller.api;

import com.sanvui.model.dto.resp.BaseResponseDto;
import com.sanvui.model.dto.resp.ColorResponseDto;
import com.sanvui.model.entity.Color;
import com.sanvui.service.ColorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 01/12/2021-6:02 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
@RequestMapping("/api/v1/colors")
public class ColorResource {

    @Autowired
    private ColorServices colorServices;

    @GetMapping
    public ResponseEntity<BaseResponseDto> findAllCustom() throws IOException {

        List<ColorResponseDto> colorResponseDtos = colorServices.findAllCustom();

        return ResponseEntity
                .ok(BaseResponseDto
                        .success("Get data success", colorResponseDtos));
    }
}
