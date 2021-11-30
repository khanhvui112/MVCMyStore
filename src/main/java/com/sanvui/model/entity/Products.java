package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 06/11/2021-2:53 PM
 * @mailto: sanvankhanh@gmail.com
 */
@ToString
@Indexed
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Products {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Field(termVector = TermVector.YES)
    @Column(name = "product_name", columnDefinition = "nvarchar(100)")
    @Size(max = 100, message = "{name.max}")
    private String productName;


    @CreatedDate
    @Column(name = "create_date", columnDefinition = "date")
    private LocalDate createDate;

    @Field(termVector = TermVector.YES)
    @Column(name = "title", columnDefinition = "nvarchar(200)")
    @Size(max = 200, message = "{name.max}")
    private String title;


    @Field(termVector = TermVector.YES)
    @Column(name = "description", columnDefinition = "nvarchar(2000)")
    @Size(max = 2000, message = "{name.max}")
    private String description;



    @Column(name = "ca_id", nullable = false)
    private int ca_id;


    @Column(name = "ma_id", nullable = false)
    private int ma_id;


    @Column(name = "sale_code")
    private String sale_code;


    @NotNull(message = "{range.null}")
    @Column(name = "color_id", nullable = false)
    private int color_id;

    @Column(name = "product_detail_id")
    private int product_detail_id;

    @Column(name = "image_link")
    private String imageLink;

    @Column(name = "price")
    private String price;

    //    Mapping to Category by ca_Id
    @ManyToOne
    @JoinColumn(name = "ca_id", referencedColumnName = "ca_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "category")
    @ToString.Exclude
    private Category category;

    //    Mapping to Manufacturer by ma_Id
    @ManyToOne
    @JoinColumn(name = "ma_id", referencedColumnName = "ma_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "manufacturer")
    @ToString.Exclude
    private Manufacturer manufacturer;

    //    mapping to Sales by sale_Id
    @ManyToOne
    @JoinColumn(name = "sale_code", referencedColumnName = "sale_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "sale")
    @ToString.Exclude
    private Sales sales;


    //    Mapping to Color by colorl_Id
    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "color_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "color")
    @ToString.Exclude
    private Color color;

    //Mapper to Image by product
    @OneToMany(mappedBy = "product", orphanRemoval = true
            , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "image")
    @ToString.Exclude
    private List<Images> imagesList;

    //  Mapper OrderDetail
    @ManyToOne
    @JoinColumn(name = "product_detail_id", referencedColumnName = "product_detail_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "productDetail")
    @ToString.Exclude
    private ProductDetail productDetail;

    //  Mapper OrderDetail
    @OneToMany(mappedBy = "product", orphanRemoval = true
            , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value="orderDetailsList")
    @ToString.Exclude
    private List<OrderDetails> orderDetailsList;

    //    mapper to Rates
    @OneToMany(mappedBy = "product", orphanRemoval = true
            , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "jtoproduct")
    @ToString.Exclude
    private List<Rates> ratesList;


}
