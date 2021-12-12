package com.sanvui.service;

import com.sanvui.model.dao.Imp.EmployeeDaoImp;
import com.sanvui.model.dto.SalaryDTO;

import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 09/11/2021-3:26 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class SalaryDTOServices {
    public List<SalaryDTO> getSalaryDTOS() {
        EmployeeDaoImp employeeDaoImp = new EmployeeDaoImp();
        List<SalaryDTO> salaryDTOS = calculatorSalaryReal(employeeDaoImp.salaryDtoList());
        return salaryDTOS;
    }

    public List<SalaryDTO> calculatorSalaryReal(List<SalaryDTO> salaryDTOS) {
        // TODO :
        for (SalaryDTO salaryDTO : salaryDTOS) {
            long daysBetween = ChronoUnit.DAYS.between(salaryDTO.getStartDate(), salaryDTO.getEndDate());
            double salaryDay = 0;
            float minusSalary = 0;
            if (daysBetween < 30) {
                salaryDay = salaryDTO.getSalary() / 30;
                long numberDay = 30 - daysBetween;
                int day = (int) numberDay;
                minusSalary = day * (float) salaryDay;
            }
            double realSalary = (salaryDTO.getSalary() + salaryDTO.getBonus()) - minusSalary;
            salaryDTO.setMinusSalary(minusSalary);
            salaryDTO.setRealSalary(realSalary);
        }
        return salaryDTOS;
    }
}
