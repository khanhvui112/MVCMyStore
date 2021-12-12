package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@ToString
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rates")
public class Rates {
    @Id
    @Column(name = "rate_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rateId;

    @NonNull
    @Column(name = "number_rate")
    @Range(min = 0, max = 5, message = "{range.size}")
    private int numberRate;

    @NonNull
    @Column(name = "comments", columnDefinition = "nvarchar(255)")
    @Size(max = 255, message = "{name.max}")
    private String comments;

    @NonNull
    @Column(name = "product_id")
    private int product_Id;

    @NonNull
    @NotNull(message = "Employee Id can't null.")
    @Column(name = "emp_id", nullable = false)
    private int emp_id;

    @NonNull
    @Pattern(regexp = "^(bought|not buy)$", message = "Status only accept bought or not buy.")
    private String status;

    @NonNull
    @Column(name = "date", columnDefinition = "datetime", nullable = false)
    private LocalDateTime date;

    //    mapping to Employee
    @ManyToOne(optional = false)
    @JoinColumn(name = "emp_id", columnDefinition = "emp_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "rate")
    @ToString.Exclude
    private Employee employee;

    //    mapping to Employee
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", columnDefinition = "product_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "jtoproduct")
    @ToString.Exclude
    private Products product;

}
