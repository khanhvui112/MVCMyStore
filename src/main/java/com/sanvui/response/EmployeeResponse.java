package com.sanvui.response;

import lombok.ToString;

import java.util.Map;

/**
 * @author: VuiSK
 * @created: 27/11/2021-8:17 PM
 * @mailto: sanvankhanh@gmail.com
 */
@ToString
public class EmployeeResponse {
    private boolean validated;
    private Map<String, String> errorMessages;

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }
}
