package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sanvui.model.dto.resp.ImagesResponseDto;
import com.sanvui.model.dto.resp.ProductResponseDto;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 06/11/2021-2:53 PM
 * @mailto: sanvankhanh@gmail.com
 */
@SqlResultSetMapping(
        name = "productCustomResultMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ProductResponseDto.class,
                        columns = {
                                @ColumnResult(name = "product_id", type = Integer.class),
                                @ColumnResult(name = "product_name", type = String.class),
                                @ColumnResult(name = "create_date", type = LocalDateTime.class),
                                @ColumnResult(name = "update_date", type = LocalDateTime.class),
                                @ColumnResult(name = "title", type = String.class),
                                @ColumnResult(name = "description", type = String.class),
                                @ColumnResult(name = "ca_id", type = Integer.class),
                                @ColumnResult(name = "ma_id", type = Integer.class),
                                @ColumnResult(name = "sale_code", type = String.class),
                                @ColumnResult(name = "color_id", type = Integer.class),
                                @ColumnResult(name = "price", type = Double.class),
                                @ColumnResult(name = "price_sales", type = Double.class)
                        }
                )
        }
)
@ToString
@Indexed
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "products")
public class Products {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Field(termVector = TermVector.YES)
    @Column(name = "product_name", columnDefinition = "nvarchar(100)")
    @Size(max = 100, message = "{name.max}")
    private String productName;


    @CreatedDate
    @Column(name = "create_date", columnDefinition = "datetime")
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "update_date", columnDefinition = "datetime")
    private LocalDateTime updateDate;

    @Field(termVector = TermVector.YES)
    @Column(name = "title", columnDefinition = "nvarchar(200)")
    @Size(max = 200, message = "{name.max}")
    private String title;


    @Field(termVector = TermVector.YES)
    @Column(name = "description", columnDefinition = "nvarchar(2000)")
    @Size(max = 2000, message = "{name.max}")
    private String description;


    @Column(name = "ca_id", nullable = false)
    private Integer ca_id;


    @Column(name = "ma_id", nullable = false)
    private Integer ma_id;


    @Column(name = "sale_code")
    private String saleCode;

    @Column(name = "image_link")
    @NotBlank
    private String imageLink;

    @Column(name = "price", precision = 10, scale = 2)
    private Double price;

    @Column(name = "price_sales", columnDefinition = "varchar(30)")
    private String priceSales;

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

    //Mapper to Image by product
    @OneToMany(mappedBy = "product", orphanRemoval = true
            , cascade = CascadeType.ALL)
    @JsonManagedReference(value = "image")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<Images> imagesList;

    //  Mapper OrderDetail
    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonBackReference(value = "productDetail")
    @ToString.Exclude
    private List<ProductDetails> productDetails;

    //  Mapper OrderDetail
    @OneToMany(mappedBy = "product", orphanRemoval = true
            , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "orderDetailsList")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<OrderDetails> orderDetailsList;


    //    mapper to Rates
    @OneToMany(mappedBy = "product", orphanRemoval = true
            , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "jtoproduct")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<Rates> ratesList;

    //    Mapping to ProductSpecification
    @OneToOne(mappedBy = "product", orphanRemoval = true
            , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "productSpecification")
    @ToString.Exclude
    private ProductSpecification productSpecification;

    public Products(String productName, String price, String title, String description, int caId, int colorId, int maId, int productDetailId) {
    }
}
