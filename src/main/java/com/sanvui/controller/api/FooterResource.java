package com.sanvui.controller.api;

import com.sanvui.convert.FooterConvert;
import com.sanvui.model.dto.resp.BaseResponseDto;
import com.sanvui.model.entity.Footer;
import com.sanvui.service.FooterService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: VuiSK
 * @created: 17/11/2021-2:26 PM
 * @mailto: sanvankhanh@gmail.com
 */
@RestController
@RequestMapping("/api/v1/footers")
public class FooterResource {
    private final FooterService footerService;

    public FooterResource(FooterService footerService) {
        this.footerService = footerService;
    }

    @GetMapping
    public ResponseEntity<BaseResponseDto> getAll() {
        List<Footer> footers = footerService.findAll().stream().map(
                f -> FooterConvert.getInstance().toDTO(f)
        ).collect(Collectors.toList());

        footers = FooterConvert.removeByStatus(footers, 0);

        if (CollectionUtils.isNotEmpty(footers)) {
            return ResponseEntity
                    .ok(BaseResponseDto
                            .success("Get Footer success", footers));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(BaseResponseDto
                        .error("Get Footer faid. Not found exception", "404"));

    }
}
