package com.sanvui.services;

import com.sanvui.dao.Imp.BaseDaoImp;
import com.sanvui.entity.FooterDetail;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: VuiSK
 * @created: 17/11/2021-2:40 PM
 * @mailto: sanvankhanh@gmail.com
 */

class FooterDetailServicesTest {

    @Test
    void inserts() {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = LocalDateTime.now().format(formatter1);
        System.out.println(dateTime);
        LocalDateTime date1= LocalDateTime.parse(dateTime, formatter1);

        List<FooterDetail> footerDetails = new ArrayList<>();
        FooterDetail footer = new FooterDetail(1,"Mua hàng trả góp",
                "./buy-product-installment",date1,1);
        FooterDetail footer1 = new FooterDetail(1,"Hướng dẫn đặt hàng và thanh toán",
                "./buy-product-help",date1,1);
        FooterDetail footer2 = new FooterDetail(1,"Chính sách bảo hành",
                "./product-insurance",date1,1);
        FooterDetail footer3 = new FooterDetail(1,"Tra cứu đơn hàng",
                "./product-check-bill",date1,1);
        FooterDetail footer4 = new FooterDetail(1,"Chính sách bảo mật",
                "./sercurity",date1,1);
        FooterDetail footer5 = new FooterDetail(1,"Chính sách hủy giao dịch và đổi trả",
                "./product-cancel-back",date1,1);

        FooterDetail footer6 = new FooterDetail(2,"Chăm sóc khách hàng",
                "./buy-product-installment",date1,1);
        FooterDetail footer7 = new FooterDetail(2,"Hỗ trợ kỹ thuật",
                "./buy-product-help",date1,1);
        FooterDetail footer8 = new FooterDetail(2,"Hỗ trợ Bảo hành và sửa chữa",
                "./product-insurance",date1,1);

        FooterDetail footer9 = new FooterDetail(3,"Danh sách chuỗi cửa hàng",
                "./list-store",date1,1);

        FooterDetail footer10 = new FooterDetail(4,"0985.574.580",
                "tel:",date1,1);

        FooterDetail footer11 = new FooterDetail(5,"VISA",
                "tel:",date1,0);
        FooterDetail footer12 = new FooterDetail(5,"ATM",
                "tel:",date1,0);

        footerDetails.add(footer);
        footerDetails.add(footer1);
        footerDetails.add(footer2);
        footerDetails.add(footer3);
        footerDetails.add(footer4);
        footerDetails.add(footer5);
        footerDetails.add(footer6);
        footerDetails.add(footer7);
        footerDetails.add(footer8);
        footerDetails.add(footer9);
        footerDetails.add(footer10);
        footerDetails.add(footer11);
        footerDetails.add(footer12);

        BaseDaoImp imp = new BaseDaoImp(FooterDetail.class);
        assertTrue(imp.insert(footerDetails));



    }
}