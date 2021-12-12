package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author: VuiSK
 * @created: 07/11/2021-1:05 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Indexed
@ToString
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private int payId;

    @NonNull
    @Column(name = "emp_id")
    private int emp_id;

    @NonNull
    @Column(name = "order_id", nullable = false)
    private int order_id;

    @NonNull
    @Column(name = "amount", columnDefinition = "decimal(10,2)"
            , nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{amount.null}")
    private double amount;

    @NonNull
    @Column(name = "employee_name_payment", columnDefinition = "nvarchar(100)")
    @NotNull(message = "{name.null}")
    @Field(termVector = TermVector.YES)
    @Size(min = 3, max = 100, message = "{name.size}")
    private String employeeNamePayment;

    @NonNull
    @Column(name = "status", columnDefinition = "nvarchar(30) check(status ='paid' " +
            "or status= 'not paid' or status=N'Đã thanh toán' or status=N'Chưa thanh toán') ")
    @Pattern(regexp = "^(paid|not paid|Đã thanh toán|Chưa thanh toán)$", message = "{value.accept}")
    private String status;

    //    mapping to Employee by emp_Id
    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id"
            , updatable = false, insertable = false)
    @JsonBackReference(value = "payment")
    @ToString.Exclude
    private Employee employee;

    //    mapping to Employee by emp_Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id"
            , updatable = false, insertable = false)
    @JsonBackReference(value = "paymentDetails")
    @ToString.Exclude
    private Orders orders;
}
