package com.sanvui.model.dto.resp;

import com.sanvui.model.entity.Products;
import lombok.*;

import java.time.LocalDate;

/**
 * @author: VuiSK
 * @created: 05/12/2021-12:44 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRespDto {
    private int productId;
    private String productName;
    private LocalDate createDate;
    private String title;
    private String description;
    private Integer ca_id;
    private Integer ma_id;
    private String sale_code;
    private Integer color_id;
    private Integer product_detail_id;
    private String imageLink;
    private String price;
}
