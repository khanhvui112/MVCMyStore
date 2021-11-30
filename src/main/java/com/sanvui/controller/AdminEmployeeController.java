package com.sanvui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServlet;


/**
 * @author: VuiSK
 * @created: 14/11/2021-8:33 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Controller
public class AdminEmployeeController extends HttpServlet {
    @GetMapping("/admin-dashboard/admin/all-employee")
    public String allEmployee(){
        return "dashboard";
    }
}
