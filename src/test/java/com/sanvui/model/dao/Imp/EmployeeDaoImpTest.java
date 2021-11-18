package com.sanvui.model.dao.Imp;

import com.sanvui.dto.MessageValidator;
import com.sanvui.entity.Employee;
import com.sanvui.service.EmployeeServices;
import org.apache.commons.codec.binary.StringUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author: VuiSK
 * @created: 11/11/2021-2:37 AM
 * @mailto: sanvankhanh@gmail.com
 */

class EmployeeDaoImpTest {

    @Test
    void loginAccount() {
//        EmployeeDaoImp employeeDaoImp = new EmployeeDaoImp();
//        assertTrue( employeeDaoImp.findEmployeeByPhone("0985574580"));
        Employee employee = new Employee();
        employee.setFirstName("Nguyễn Thị");
        employee.setLastName("Tò");
        employee.setUserName("hoangto23");
        employee.setPassword("Va@231as565");
        employee.setPhone("0985574580");
        employee.setGender("Nữ");
        String s = " Nữ ";
        System.out.println("|"+s+"|");
        s =s.replaceAll(" ","");
        System.out.println("|"+s+"|");
        employee.setDateOfBirth(toLocalDate("1999-10-12"));
        employee.setEmail("hoanga@gmail.com");
        MessageValidator messages = EmployeeServices.getMessage(employee);
        System.out.println(messages);
    }

    @Test
    void insertEmployTest() {
        EmployeeServices services = new EmployeeServices();
        Employee employee = new Employee();
        employee.setUserName("");
        employee.setGender("Nam");
        employee.setEmail(null);
        String s = null;
        LocalDate date;
        if (s == null){
            date = null;
        }else {
            date = toLocalDate(null);
        }
        employee.setDateOfBirth(date);
        employee.setPhone("");
        employee.setPassword("");
//        services.insertUser(employee);
        MessageValidator messages = new MessageValidator();
        messages = services.getMessage(employee);
        if (messages != null) {
            System.out.println("Fail ");
            System.out.println(messages);
        }

    }

    private static  String toEncode(String s){
        byte[] bytes = StringUtils.getBytesUtf8(s);
        String newStringUtf8 =  StringUtils.newStringUtf8(bytes);
        System.out.println(newStringUtf8);
        return newStringUtf8;
    }
    private static LocalDate toLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
        return LocalDate.parse(date, formatter);
    }
}