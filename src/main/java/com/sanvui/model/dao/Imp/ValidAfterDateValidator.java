package com.sanvui.model.dao.Imp;

import com.sanvui.model.dao.ValidAfterDate;
import com.sanvui.model.entity.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author: VuiSK
 * @created: 04/11/2021-12:11 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class ValidAfterDateValidator implements ConstraintValidator<ValidAfterDate, Object> {


    @Override
    public void initialize(ValidAfterDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        if (o == null) {
            return true;
        }
//        convert o to String
        String s = o.toString();

        int check = s.indexOf("@");
        String sub = null;
        if(check != -1){
            int endIndex = s.lastIndexOf("@");
            int startIndex = s.lastIndexOf(".");
            sub = s.substring(startIndex+1,endIndex);
        }else{
            int index = s.indexOf("{");
            sub = s.substring(0, index);
        }
        if (sub.equalsIgnoreCase("Jobs")){
            Jobs jobs = (Jobs) o;
            return jobs.getEndDate().compareTo(jobs.getStartDate()) > 0;
        }
        if (sub.equalsIgnoreCase("Sales")){
            Sales sales =(Sales) o;
            return sales.getEndDate().compareTo(sales.getStartDate()) > 0;
        }
        if (sub.equalsIgnoreCase("Orders")){
            Orders sales =(Orders) o;
            return sales.getSentDate().compareTo(sales.getOrderDate()) >= 0
                    && sales.getReceivedDate().compareTo(sales.getSentDate()) >= 0;
        }
        if (sub.equalsIgnoreCase("Discount")){
            Discount sales =(Discount) o;
            return sales.getEndDate().compareTo(sales.getStartDate()) > 0;
        }
        if (sub.equalsIgnoreCase("Salary")){
            Salary sales =(Salary) o;
            return sales.getEndDate().compareTo(sales.getStartDate()) > 0;
        }
        return true;

    }

}
