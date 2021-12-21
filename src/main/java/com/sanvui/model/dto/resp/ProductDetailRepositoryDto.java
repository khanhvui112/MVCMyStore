package com.sanvui.model.dto.resp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sanvui.model.entity.Color;
import com.sanvui.model.entity.ProductDetails;
import com.sanvui.model.entity.Products;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author: VuiSK
 * @created: 18/12/2021-5:34 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDetailRepositoryDto {

    private int productDetailId;

    private Integer colorId;

    private Integer quantity;

    private Double price;

    private Double priceSales;

    private Integer productId;

    private String type;
}
