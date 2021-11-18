package com.sanvui.entity;

import com.sanvui.dao.ValidAfterDate;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 07/11/2021-5:04 PM
 * @mailto: sanvankhanh@gmail.com
 */
@ValidAfterDate
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Table(name = "Discount")
public class Discount {

    @Id
    @Column(name = "discount_Code")
    @Pattern(regexp = "^[a-zA-Z]{3,5}+([0-9]{2,10})$", message = "{discount.code.valid}")
    private String discountCode;

    @NonNull
    @Column(name = "discount_Type")
    private String discountType;

    @NonNull
    @Column(name = "discount_Min_Price")
    private int discountMinPrice;

    @NonNull
    @Column(name = "discount_Max_Price")
    private int discountMaxPrice;

    @NonNull
    @Column(name = "start_Date",nullable = false)
    private LocalDate startDate;

    @NonNull
    @Column(name = "end_Date", nullable = false)
    private LocalDate endDate;

    @NonNull
    @NotNull
    @Column(name = "status",columnDefinition = "nvarchar(30) check(status='success' or status='repay' " +
            "or status='pending' or status='shipping')")
    @Pattern(regexp = "^(ready|too limit|error|used)$",message = "{payment.status.valid}")
    private String status;

//    Mapper to Orders
    @OneToMany(mappedBy = "discount",orphanRemoval = true
            , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Orders> orders;


    public Discount(String discountCode, @NonNull String discountType, @NonNull int discountMinPrice, @NonNull int discountMaxPrice, @NonNull LocalDate startDate, @NonNull LocalDate endDate, @NonNull String status) {
        this.discountCode = discountCode;
        this.discountType = discountType;
        this.discountMinPrice = discountMinPrice;
        this.discountMaxPrice = discountMaxPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discountCode='" + discountCode + '\'' +
                ", discountType='" + discountType + '\'' +
                ", discountMinPrice=" + discountMinPrice +
                ", discountMaxPrice=" + discountMaxPrice +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}
