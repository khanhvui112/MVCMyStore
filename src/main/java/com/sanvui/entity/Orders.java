package com.sanvui.entity;

import com.sanvui.dao.ValidAfterDate;
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
@Indexed
@Table(name = "Orders")
public class Orders {
    @Id
    @Column(name = "order_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @NonNull
    @NotNull
    @Field(termVector = TermVector.YES)
    @Column(name = "emp_Name", nullable = false)
    private String empName;

    @NonNull
    @NotNull
    @Field(termVector = TermVector.YES)
    @Column(name = "order_Date", nullable = false)
    private LocalDate orderDate;

    @NonNull
    @NotNull
    @Field(termVector = TermVector.YES)
    @Column(name = "sent_Date")
    private LocalDate sentDate;

    @NonNull
    @NotNull
    @Field(termVector = TermVector.YES)
    @Column(name = "received_Date")
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
    @Column(name = "order_Price", columnDefinition = "decimal(10,2)", precision = 10, scale = 2)
    private double orderPrice;

    @NonNull
    @Column(name = "discount_Code")
    private String discount_Code;

    @NonNull
    @Column(name = "emp_Id")
    private int emp_Id;

    //    mapping to Employee
    @ManyToOne
    @JoinColumn(name = "emp_Id", referencedColumnName = "emp_Id"
            , insertable = false, updatable = false)
    private Employee employee;

    //    Mapper to OrderDetail
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL
            , fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderDetails> orderDetails;

    //    Mapping to Discount
    @ManyToOne
    @JoinColumn(name = "discount_Code", referencedColumnName = "discount_Code"
            , insertable = false, updatable = false)
    private Discount discount;

    //    Mapper to OrderDetail
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL
            , fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Payment> paymentDetails;

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", empName='" + empName + '\'' +
                ", orderDate=" + orderDate +
                ", sentDate=" + sentDate +
                ", receivedDate=" + receivedDate +
                ", status='" + status + '\'' +
                ", notes='" + notes + '\'' +
                ", orderPrice=" + orderPrice +
                ", discountCode='" + discount_Code + '\'' +
                ", emp_Id=" + emp_Id +
                '}';
    }
}
