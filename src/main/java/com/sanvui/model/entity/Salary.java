package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sanvui.model.dao.ValidAfterDate;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author: VuiSK
 * @created: 09/11/2021-2:38 PM
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
@Table(name = "salary")
public class Salary {
    @Id
    @Column(name = "salary_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salaryId;

    @NonNull
    @Column(name = "emp_id", nullable = false)
    private int emp_Id;

    @NonNull
    @Column(name = "start_date", columnDefinition = "date")
    private LocalDate startDate;

    @NonNull
    @Column(name = "end_date", columnDefinition = "date")
    private LocalDate endDate;

    @NonNull
    @Column(name = "salary", precision = 10, scale = 2, columnDefinition = "decimal(10,2)")
    private double salary;

    @NonNull
    @Column(name = "bonus", precision = 10, scale = 2, columnDefinition = "decimal(10,2)")
    private double bonus;

    //    mapping to Employee
    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "salary")
    @ToString.Exclude
    private Employee employee;

}
