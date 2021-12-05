package com.sanvui.controller;

import com.sanvui.model.entity.Color;
import com.sanvui.service.ColorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 01/12/2021-6:02 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
@RequestMapping("/api/v1/color")
public class ColorController {
    @Autowired
    private ColorServices colorServices;

    @GetMapping("/find-all")
    public List<Color> findAll() {
        return colorServices.findAll();
    }
}
