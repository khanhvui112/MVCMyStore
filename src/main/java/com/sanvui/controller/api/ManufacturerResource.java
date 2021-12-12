package com.sanvui.controller.api;

import com.sanvui.model.dto.resp.BaseResponseDto;
import com.sanvui.model.dto.resp.ManufacturerResponseDto;
import com.sanvui.model.entity.Manufacturer;
import com.sanvui.service.ManufacturerService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 01/12/2021-6:11 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
@RequestMapping("/api/v1/manufacturers")
public class ManufacturerResource {
    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity findAll() throws IOException {
        List<ManufacturerResponseDto> manufacturers = manufacturerService.findAllCustom();
        if (CollectionUtils.isNotEmpty(manufacturers)) {
            return ResponseEntity.
                    ok(BaseResponseDto
                            .success("Get Manufacturer success", manufacturers));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(BaseResponseDto
                        .error("Get Manufacturer fail. Not found exception", "404"));
    }
}
