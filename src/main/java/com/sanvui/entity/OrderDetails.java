package com.sanvui.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author: VuiSK
 * @created: 07/11/2021-3:48 PM
 * @mailto: sanvankhanh@gmail.com
 */

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OrderDetails")
public class OrderDetails {
    @Id
    @Column(name = "order_Detail_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailId;

    @NonNull
    @NotNull(message = "order Id can't be null")
    @Column(name = "order_Id", nullable = false)
    private int order_Id;

    @NonNull
    @NotNull(message = "product Id can't be null")
    @Column(name = "product_Id", nullable = false)
    private int product_Id;

    @Column(name = "quantity")
    @NonNull
    private int quantity;

    @NonNull
    @Column(name = "price", precision = 10, scale = 2)
    private double price;

    //    mapping to Orders by order_Id
    @ManyToOne
    @JoinColumn(name = "order_Id", referencedColumnName = "order_Id"
            , insertable = false, updatable = false)
    private Orders orders;

    //    mapping to Product by order_Id
    @ManyToOne
    @JoinColumn(name = "product_Id", referencedColumnName = "product_Id"
            , insertable = false, updatable = false)
    private Products product;

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderDetailId=" + orderDetailId +
                ", order_Id=" + order_Id +
                ", product_Id=" + product_Id +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
