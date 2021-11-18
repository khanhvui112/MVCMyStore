package com.sanvui.entity;

import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

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

@Indexed
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Table(name = "Products")
public class Products {

    @Id
    @Column(name = "product_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @NonNull
    @Field(termVector = TermVector.YES)
    @Column(name = "product_Name", columnDefinition = "nvarchar(100)")
    @Size(max = 100, message = "{name.max}")
    private String productName;

    @NonNull
    @Column(name = "create_Date", columnDefinition = "date")
    private LocalDate createDate;

    @NonNull
    @Field(termVector = TermVector.YES)
    @Column(name = "title", columnDefinition = "nvarchar(200)")
    @Size(max = 200, message = "{name.max}")
    private String title;

    @NonNull
    @Field(termVector = TermVector.YES)
    @Column(name = "description", columnDefinition = "nvarchar(2000)")
    @Size(max = 2000, message = "{name.max}")
    private String description;


    @NonNull
    @Column(name = "ca_Id", nullable = false)
    private int ca_Id;

    @NonNull
    @Column(name = "ma_Id", nullable = false)
    private int ma_Id;

    @NonNull
    @Column(name = "sale_Id")
    private int sale_Id;

    @NonNull
    @NotNull(message = "{range.null}")
    @Column(name="color_Id", nullable = false)
    private int color_Id;

    @NonNull
    @Column(name = "product_Detail_Id")
    private int product_Detail_Id;

//    Mapping to Category by ca_Id
    @ManyToOne
    @JoinColumn(name = "ca_Id", referencedColumnName = "ca_Id"
            ,insertable = false, updatable = false )
    private Category category;

//    Mapping to Manufacturer by ma_Id
    @ManyToOne
    @JoinColumn(name = "ma_Id", referencedColumnName = "ma_Id"
            ,insertable = false, updatable = false )
    private Manufacturer manufacturer;

//    mapping to Sales by sale_Id
    @ManyToOne
    @JoinColumn(name = "sale_Id", referencedColumnName = "sale_Id"
            , insertable = false, updatable = false)
    private Sales sales;


//    Mapping to Color by colorl_Id
    @ManyToOne
    @JoinColumn(name = "color_Id", referencedColumnName = "color_Id"
            , insertable = false, updatable = false)
    private Color color;

//Mapper to Image by product
    @OneToMany(mappedBy = "product", orphanRemoval = true
            , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Images> imagesList;

//  Mapper OrderDetail
    @ManyToOne
    @JoinColumn(name = "product_Detail_Id",referencedColumnName = "product_Detail_Id"
            , insertable = false, updatable = false)
    private ProductDetail productDetail;

    //  Mapper OrderDetail
    @OneToMany(mappedBy = "product", orphanRemoval = true
            , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetailsList;

//    mapper to Rates
    @OneToMany(mappedBy = "product",orphanRemoval = true
            , fetch =FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rates> ratesList;


    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", createDate=" + createDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ca_Id=" + ca_Id +
                ", ma_Id=" + ma_Id +
                ", sale_Id=" + sale_Id +
                ", color_Id=" + color_Id +
                ", product_Detail_Id=" + product_Detail_Id +
                '}';
    }
}
