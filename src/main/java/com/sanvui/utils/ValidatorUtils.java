package com.sanvui.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author: VuiSK
 * @created: 03/11/2021-2:21 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class ValidatorUtils {
    public static ValidatorBean validatorBean(Object o){
//           CREATE VALIDATION FACTORY
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations= validator.validate(o);
        if (violations.isEmpty()){
            return new ValidatorBean("Your bean is OK");
        }else {
            return new ValidatorBean(violations);
        }
    }

}
