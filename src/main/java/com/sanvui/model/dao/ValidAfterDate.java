package com.sanvui.model.dao;

import com.sanvui.model.dao.Imp.ValidAfterDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author: VuiSK
 * @created: 04/11/2021-12:09 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidAfterDateValidator.class })
@Documented
public @interface ValidAfterDate {
    String message() default "The end date should be after the starting date || The sent Date can bigger than or equal " +
            "order Date and received Date can bigger than sent Date or equal to." ;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
