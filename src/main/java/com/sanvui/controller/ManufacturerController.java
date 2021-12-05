package com.sanvui.controller;

import com.sanvui.model.entity.Manufacturer;
import com.sanvui.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 01/12/2021-6:11 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
@RequestMapping("/api/v1/manufacturer")
public class ManufacturerController {
    @Autowired
    private ManufacturerService services;

    @GetMapping("/find-all")
    public List<Manufacturer> findAll() {
        return services.findAll();
    }
}
