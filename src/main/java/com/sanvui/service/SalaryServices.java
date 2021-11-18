package com.sanvui.service;

import com.sanvui.entity.Salary;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 10/11/2021-1:09 AM
 * @mailto: sanvankhanh@gmail.com
 */

public class SalaryServices {
    public boolean insert(List<Salary> salaryList){
        BaseServices services = new BaseServices(Salary.class);
        return services.insert(salaryList);
    }
}
