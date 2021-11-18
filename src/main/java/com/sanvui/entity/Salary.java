package com.sanvui.entity;

import com.sanvui.dao.ValidAfterDate;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author: VuiSK
 * @created: 09/11/2021-2:38 PM
 * @mailto: sanvankhanh@gmail.com
 */
@ValidAfterDate
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Table(name = "Salary")
public class Salary {
    @Id
    @Column(name = "salary_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salaryId;

    @NonNull
    @Column(name = "emp_Id", nullable = false)
    private int emp_Id;

    @NonNull
    @Column(name = "start_Date", columnDefinition = "date")
    private LocalDate startDate;

    @NonNull
    @Column(name = "end_Date", columnDefinition = "date")
    private LocalDate endDate;

    @NonNull
    @Column(name = "salary", precision = 10, scale = 2, columnDefinition = "decimal(10,2)")
    private double salary;

    @NonNull
    @Column(name = "bonus", precision = 10, scale = 2, columnDefinition = "decimal(10,2)")
    private double bonus;

//    mapping to Employee
    @ManyToOne
    @JoinColumn(name = "emp_Id", referencedColumnName = "emp_Id"
            , insertable = false, updatable = false)
    private Employee employee;

    @Override
    public String toString() {
        return "Salary{" +
                "salaryId=" + salaryId +
                ", emp_Id=" + emp_Id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", salary=" + salary +
                ", bonus=" + bonus +
                '}';
    }
}
