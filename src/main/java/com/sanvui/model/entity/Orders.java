package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sanvui.model.dao.ValidAfterDate;
import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 07/11/2021-2:39 PM
 * @mailto: sanvankhanh@gmail.com
 */
@ValidAfterDate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@ToString
@Indexed
@Table(name = "orders")
public class Orders {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @NonNull
    @NotNull
    @Field(termVector = TermVector.YES)
    @Column(name = "emp_name", nullable = false)
    private String empName;

    @NonNull
    @NotNull
    @Field(termVector = TermVector.YES)
    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @NonNull
    @NotNull
    @Field(termVector = TermVector.YES)
    @Column(name = "sent_date")
    private LocalDate sentDate;

    @NonNull
    @NotNull
    @Field(termVector = TermVector.YES)
    @Column(name = "received_date")
    private LocalDate receivedDate;

    @NonNull
    @NotNull
    @Column(name = "status", columnDefinition = "nvarchar(30) check(status='success' or status='repay' " +
            "or status='pending' or status='shipping')")
    @Pattern(regexp = "^(success|repay|pending|shipping)$", message = "{payment.status.valid}")
    private String status;

    @NonNull
    @Column(name = "notes")
    private String notes;

    @NonNull
    @Column(name = "order_price", columnDefinition = "decimal(10,2)", precision = 10, scale = 2)
    private double orderPrice;

    @NonNull
    @Column(name = "discount_code")
    private String discount_Code;

    @NonNull
    @Column(name = "emp_id")
    private int emp_id;

    //    mapping to Employee
    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "order" )
    @ToString.Exclude
    private Employee employee;

    //    Mapper to OrderDetail
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL
            , fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference(value = "orderDetails" )
    @ToString.Exclude
    private List<OrderDetails> orderDetails;

    //    Mapping to Discount
    @ManyToOne
    @JoinColumn(name = "discount_code", referencedColumnName = "discount_code"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "discount" )
    @ToString.Exclude
    private Discount discount;

    //    Mapper to OrderDetail
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL
            , fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference(value = "paymentDetails" )
    @ToString.Exclude
    private List<Payment> paymentDetails;

}
