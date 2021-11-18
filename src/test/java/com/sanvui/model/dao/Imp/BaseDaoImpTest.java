package com.sanvui.model.dao.Imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sanvui.convert.EmployeeConvert;
import com.sanvui.dao.Imp.BaseDaoImp;
import com.sanvui.dao.Imp.EmployeeDaoImp;
import com.sanvui.dto.EmployeeDTO;
import com.sanvui.entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: VuiSK
 * @created: 14/11/2021-10:54 PM
 * @mailto: sanvankhanh@gmail.com
 */

class BaseDaoImpTest {

    @Test
    void findAll() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        BaseDaoImp imp = new BaseDaoImp(Employee.class);
        EmployeeDaoImp employeeDaoImp = new EmployeeDaoImp();
        //Define map which will be converted to JSON
        List<Employee> employees = imp.findAll();

        List<EmployeeDTO> dtos = employees.stream().map((e) ->
                EmployeeConvert.getInstance().toDTO(e)).collect(Collectors.toList());
        dtos = EmployeeConvert.removeUser(dtos);
        //1. Convert List of Person objects to JSON
        String arrayToJson = objectMapper.writeValueAsString(dtos);
        System.out.println("1. Convert List of person objects to JSON :");
        System.out.println(arrayToJson);
    }

    @Test
    void findById() throws JsonProcessingException {

    }

    @Test
    void insert() {
        BaseDaoImp imp = new BaseDaoImp(Menu.class);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = LocalDateTime.now().format(formatter1);
        System.out.println(dateTime);
        LocalDateTime date1= LocalDateTime.parse(dateTime, formatter1);

        System.out.println(date1);

        List<Menu> menus = new ArrayList<>();
        Menu menu = new Menu("Trang chủ", "/home",date1,0,1);
        Menu menu1 = new Menu("Sản phẩm", "/products",date1,0,1);
        Menu menu2 = new Menu("Dịch vụ", "/services",date1,0,1);
        Menu menu3 = new Menu("Liên hệ", "/contacts",date1,0,1);
        Menu menu4 = new Menu("Đăng nhập", "/login",date1,0,1);
        Menu menu5 = new Menu("Đăng xuất", "/Logout",date1,1,1);
        menus.add(menu);
        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        menus.add(menu4);
        menus.add(menu5);
        imp.insert(menus);



//        Menu color = new Menu(1,"Yellow");
//        Menu color1 = new Menu("Pink");
//        Menu color2 = new Menu("Blue");
//        List<Color> colors = new ArrayList<>();
//        colors.add(color);
//        colors.add(color1);
//        colors.add(color2);
//        imp.insert(colors);
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}