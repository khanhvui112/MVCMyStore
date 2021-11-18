package com.sanvui.entity;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author: VuiSK
 * @created: 07/11/2021-6:37 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter @Setter @AllArgsConstructor @RequiredArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "Rates")
public class Rates {
    @Id
    @Column(name = "rate_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rateId;

    @NonNull
    @Column(name = "number_Rate")
    @Range(min = 0, max = 5, message = "{range.size}")
    private int numberRate;

    @NonNull
    @Column(name = "comments", columnDefinition = "nvarchar(255)")
    @Size(max = 255, message = "{name.max}")
    private String comments;

    @NonNull
    @Column(name = "product_Id")
    private int product_Id;

    @NonNull
    @NotNull(message = "Employee Id can't null.")
    @Column(name = "emp_Id", nullable = false)
    private int emp_Id;

    @NonNull
    @Pattern(regexp = "^(bought|not buy)$", message = "Status only accept bought or not buy.")
    private String status;

    @NonNull
    @Column(name = "date", columnDefinition = "datetime",nullable = false)
    private LocalDateTime date;

//    mapping to Employee
    @ManyToOne(optional = false)
    @JoinColumn(name = "emp_Id", columnDefinition = "emp_Id"
            , insertable = false, updatable = false)
    private Employee employee;

    //    mapping to Employee
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_Id", columnDefinition = "product_Id"
            , insertable = false, updatable = false)
    private Products product;

    @Override
    public String toString() {
        return "Rates{" +
                "rateId=" + rateId +
                ", numberRate=" + numberRate +
                ", comments='" + comments + '\'' +
                ", product_Id=" + product_Id +
                ", emp_Id=" + emp_Id +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }
}
