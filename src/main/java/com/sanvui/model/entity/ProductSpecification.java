package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author: VuiSK
 * @created: 15/12/2021-7:53 PM
 * @mailto: sanvankhanh@gmail.com
 */
@ToString
@Indexed
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productspecification")
public class ProductSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_specification_id")
    private int productSpecificationId;

    @Column(name = "screen", columnDefinition = "nvarchar(255)")
    @Size(max = 255, message = "{name.max}")
    private String screen;


    @Column(name = "resolution", columnDefinition = "nvarchar(255)")
    @Size(max = 255, message = "{name.max}")
    private String resolution;


    @Column(name = "operating_system", columnDefinition = "nvarchar(255)")
    @Size(max = 255, message = "{name.max}")
    private String operatingSystem;


    @Field(termVector = TermVector.YES)
    @Column(name = "CPU", columnDefinition = "nvarchar(255)")
    @Size(max = 100, message = "{name.max}")
    private String CPU;


    @Field(termVector = TermVector.YES)
    @Column(name = "ROM", columnDefinition = "varchar(20)")
    @Size(max = 20, message = "{name.max}")
    private String ROM;


    @Column(name = "RAM", columnDefinition = "varchar(20)")
    @Size(max = 20, message = "{name.max}")
    private String RAM;


    @Column(name = "mobile_network", columnDefinition = "nvarchar(100)")
    @Size(max = 100, message = "{name.max}")
    private String mobileNetwork;


    @Column(name = "sim_card", columnDefinition = "nvarchar(50)")
    @Size(max = 50, message = "{name.max}")
    private String simCard;

    @Column(name = "screen_size", columnDefinition = "nvarchar(50)")
    @Size(max = 50, message = "{name.max}")
    private String screenSize;

    @Column(name = "weight", columnDefinition = "nvarchar(255)")
    @Size(max = 255, message = "{name.max}")
    private String weight;

    @Column(name = "battery", columnDefinition = "nvarchar(255)")
    @Size(max = 255, message = "{name.max}")
    private String battery;

    @Column(name = "product_id")
    private int product_id;

    //    Mapping to Product Detail
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "productSpecification")
    @ToString.Exclude
    private Products product;

}
