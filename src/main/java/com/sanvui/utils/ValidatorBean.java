package com.sanvui.utils;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: VuiSK
 * @created: 03/11/2021-2:20 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class ValidatorBean {
    private String messages;
    private boolean success;

    public ValidatorBean(String messages) {
//        true
        this.messages = messages;
        this.success = true;
    }
    public ValidatorBean(Set<? extends ConstraintViolation<?>> violations){
        this.success = false;
        this.messages = violations.stream()
                .map(msg->msg.getMessage())
                .collect(Collectors.joining("\n "));
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Al information validator"+"\n"+" {" +
                "messages=" + messages +"\t\t" +
                ", success=" + success +
                '}';
    }
}
