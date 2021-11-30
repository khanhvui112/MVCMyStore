package com.sanvui.controller;

import com.sanvui.convert.FooterConvert;
import com.sanvui.model.entity.Footer;
import com.sanvui.service.FooterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: VuiSK
 * @created: 17/11/2021-2:26 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
public class FooterController {
    private final FooterService footerService;

    public FooterController(FooterService footerService) {
        this.footerService = footerService;
    }

    @GetMapping("/footer-all")
    public List<Footer> getAll(){
        List<Footer> footers = footerService.findAll().stream().map(
                f-> FooterConvert.getInstance().toDTO(f)
        ).collect(Collectors.toList());
        footers = FooterConvert.removeByStatus(footers,0);
        return footers;
    }
}
