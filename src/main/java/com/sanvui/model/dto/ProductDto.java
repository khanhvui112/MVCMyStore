package com.sanvui.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.TermVector;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author: VuiSK
 * @created: 01/12/2021-11:05 AM
 * @mailto: sanvankhanh@gmail.com
 */

@Data
@Getter
@Setter
public class ProductDto implements Serializable {


    private String productName;

    @CreatedDate
    @Column(name = "create_date", columnDefinition = "date")
    private LocalDate createDate;


    private String title;

    private String description;

    private int ca_id;

    private int ma_id;

    private String sale_code;

    private int color_id;

    private int product_detail_id;

    private String price;

    /*    private MultipartFile image_Link;*/

    private String imageUrl;

}
