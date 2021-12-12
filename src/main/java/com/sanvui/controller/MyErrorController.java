package com.sanvui.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author: VuiSK
 * @created: 26/11/2021-5:30 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Controller
public class MyErrorController implements ErrorController {
    @RequestMapping({"/error", "/home/error"})
    public String handleError(HttpServletRequest request) {
        Integer status = (Integer)
                request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (Objects.nonNull(status)) {
            if (status.equals(HttpStatus.NOT_FOUND.value())) {
                return "error/error";
            }
            if (status.equals(HttpStatus.FORBIDDEN.value())) {
                return "error/error_access_denied";
            }
            if (status.equals(HttpStatus.INTERNAL_SERVER_ERROR.value())) {
                return "error/error500";
            }
        }
        return "error/error";
    }
}
