package com.sanvui.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sanvui.convert.FooterConvert;
import com.sanvui.convert.MenuConvert;
import com.sanvui.dto.MenuDTO;
import com.sanvui.entity.Footer;
import com.sanvui.entity.Menu;
import com.sanvui.service.FooterService;
import com.sanvui.utils.ConvertToJsonUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: VuiSK
 * @created: 17/11/2021-2:29 PM
 * @mailto: sanvankhanh@gmail.com
 */

class FooterServiceTest {

    @Test
    void inserts() {

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = LocalDateTime.now().format(formatter1);
        System.out.println(dateTime);
        LocalDateTime date1= LocalDateTime.parse(dateTime, formatter1);

        List<Footer> footers = new ArrayList<>();
        Footer footer = new Footer("Hỗ trợ - Dịch vụ",1,date1);
        Footer footer1 = new Footer("Thông tin liên hệ",1,date1);
        Footer footer2 = new Footer("Hệ thống cửa hàng",1,date1);
        Footer footer3 = new Footer("Tổng đài",1,date1);
        Footer footer4 = new Footer("Thanh toán miễn phí",0,date1);
        footers.add(footer);
        footers.add(footer1);
        footers.add(footer2);
        footers.add(footer3);
        footers.add(footer4);

        assertTrue(FooterService.inserts(footers));
    }
    @Test
    void findAll() throws JsonProcessingException {
        List<Footer> footers = FooterService.findAll();

        //            convert Menus to MenuDTO
        List<Footer> dtos = footers.stream().map((e) ->
                FooterConvert.getInstance().toDTO(e)).collect(Collectors.toList());

        System.out.println(dtos);
        //        Declare convert
        ConvertToJsonUtils converts = new ConvertToJsonUtils(Menu.class);

        dtos = FooterConvert.removeByStatus(dtos, 0);
        String jsons = converts.convertToJson(dtos);
        System.out.println("Json "+ jsons);

    }
}