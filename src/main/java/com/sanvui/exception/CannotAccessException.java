package com.sanvui.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author: VuiSK
 * @created: 26/11/2021-2:50 PM
 * @mailto: sanvankhanh@gmail.com
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class CannotAccessException extends RuntimeException {

    public CannotAccessException(){}

    public CannotAccessException(String message){
        super(message);
    }
}
