package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author: VuiSK
 * @created: 15/12/2021-8:01 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Indexed
@Table(name = "productdetails")
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_detail_id")
    private int productDetailId;

    @NotNull(message = "{range.null}")
    @Column(name = "color_id", nullable = false)
    private Integer color_id;

    @Column(name = "quantity")
    @Range(min = 0, message = "quantity cannot be small than 0")
    private Integer quantity;

    @Column(name = "price", precision = 10, scale = 2)
    private Double price;

    @Column(name = "price_sales", precision = 10, scale = 2)
    private Double priceSales;

    @NotNull(message = "product Id can't be null")
    @Column(name = "product_id", nullable = false)
    private Integer product_id;

    @Column(name = "type")
    private String type;

    @Column(name = "version")
    private String version;

    //    Mapping to Color by color_Id
    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "color_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "color")
    @ToString.Exclude
    private Color color;

    //    mapping to Product by order_Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "productDetail")
    @ToString.Exclude
    private Products product;
}
