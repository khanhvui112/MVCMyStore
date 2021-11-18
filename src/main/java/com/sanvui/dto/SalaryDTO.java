package com.sanvui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author: VuiSK
 * @created: 09/11/2021-2:56 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDTO {
    private String fullName;
    private LocalDate startDate;
    private LocalDate endDate;
    private double salary;
    private double bonus;
    private double minusSalary;
    private double realSalary;

    @Override
    public String toString() {
        return "SalaryDTO{" +
                "fullName='" + fullName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", minusSalary=" + minusSalary +
                ", realSalary=" + realSalary +
                '}';
    }
}
