package com.sanvui.model.dto.resp;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
public class ProductResponseDto {
    private int productId;
    private String productName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String title;
    private String description;
    private Integer ca_id;
    private Integer ma_id;
    private String sale_code;
    private Integer color_id;

    private List<ImagesResponseDto> imageLink;

    private List<ProductDetailRepositoryDto> productDetailRepositoryDtos;

    private Double price;
    private Double priceSales;

    public ProductResponseDto(int productId, String productName, LocalDateTime createDate, LocalDateTime updateDate, String title, String description, Integer ca_id, Integer ma_id, String sale_code, Integer color_id, Double price, Double priceSales) {
        this.productId = productId;
        this.productName = productName;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.title = title;
        this.description = description;
        this.ca_id = ca_id;
        this.ma_id = ma_id;
        this.sale_code = sale_code;
        this.color_id = color_id;
        this.price = price;
        this.priceSales = priceSales;
    }
}
