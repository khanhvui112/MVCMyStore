package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author: VuiSK
 * @created: 07/11/2021-3:48 PM
 * @mailto: sanvankhanh@gmail.com
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderdetails")
public class OrderDetails {
    @Id
    @Column(name = "order_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailId;

    @NonNull
    @NotNull(message = "order Id can't be null")
    @Column(name = "order_id", nullable = false)
    private int order_id;

    @NonNull
    @NotNull(message = "product Id can't be null")
    @Column(name = "product_id", nullable = false)
    private int product_id;

    @Column(name = "quantity")
    @NonNull
    private int quantity;

    @NonNull
    @Column(name = "price", precision = 10, scale = 2)
    private double price;

    //    mapping to Orders by order_Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "orderDetails" )
    @ToString.Exclude
    private Orders orders;

    //    mapping to Product by order_Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value="orderDetailsList")
    @ToString.Exclude
    private Products product;

}
