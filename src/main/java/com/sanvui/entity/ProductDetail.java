package com.sanvui.entity;

import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 07/11/2021-10:34 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Indexed
@Table(name = "ProductDetail")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_Detail_Id")
    private int productDetailId;

    @NonNull
    @Column(name = "screen_Technology", columnDefinition = "nvarchar(255)")
    @Size(max = 255, message = "{name.max}")
    private String screenTechnology;

    @NonNull
    @Column(name = "resolution", columnDefinition = "nvarchar(255)")
    @Size(max = 255, message = "{name.max}")
    private String resolution;

    @NonNull
    @Column(name = "operating_System", columnDefinition = "nvarchar(255)")
    @Size(max = 255, message = "{name.max}")
    private String operatingSystem;

    @NonNull
    @Field(termVector = TermVector.YES)
    @Column(name = "CPU", columnDefinition = "varchar(30)")
    @Size(max = 30, message = "{name.max}")
    private String CPU;

    @NonNull
    @Field(termVector = TermVector.YES)
    @Column(name = "ROM", columnDefinition = "varchar(20)")
    @Size(max = 20, message = "{name.max}")
    private String ROM;

    @NonNull
    @Field(termVector = TermVector.YES)
    @Column(name = "RAM", columnDefinition = "varchar(20)")
    @Size(max = 20, message = "{name.max}")
    private String RAM;

    @NonNull
    @Column(name = "mobile_Network", columnDefinition = "varchar(20)")
    @Size(max = 20, message = "{name.max}")
    private String mobileNetwork;

    @NonNull
    @Field(termVector = TermVector.YES)
    @Column(name = "slot_Sim", columnDefinition = "varchar(20)")
    @Range(min = 0, max = 4, message = "{range.max}")
    private int slotSim;

    @NonNull
    @PastOrPresent(message = "Manufacture can bigger than or equal date now")
    @Column(name = "manufacture_Of_Date", columnDefinition = "date")
    private LocalDate manufacture_Of_Date;

    //    Mapping to Products
    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL
            , fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Products> productsList;


//    TO STRING


}
