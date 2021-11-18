package com.sanvui.entity;

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
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Indexed
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_Id")
    private int pay_Id;

    @NonNull
    @Column(name = "emp_Id")
    private int emp_Id;

    @NonNull
    @Column(name = "order_Id", nullable = false)
    private int order_Id;

    @NonNull
    @Column(name = "amount", columnDefinition = "decimal(10,2)"
            , nullable = false, precision = 10, scale = 2)
    @NotNull(message = "{amount.null}")
    private double amount;

    @NonNull
    @Column(name = "employee_Name_Payment", columnDefinition = "nvarchar(100)")
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
    @JoinColumn(name = "emp_Id", referencedColumnName = "emp_Id"
            , updatable = false, insertable = false)
    private Employee employee;

    //    mapping to Employee by emp_Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_Id", referencedColumnName = "order_Id"
            , updatable = false, insertable = false)
    private Orders orders;
    @Override
    public String toString() {
        return "Payment{" +
                "pay_Id=" + pay_Id +
                ", emp_Id=" + emp_Id +
                ", order_Id=" + order_Id +
                ", amount=" + amount +
                ", employeeNamePayment='" + employeeNamePayment + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
