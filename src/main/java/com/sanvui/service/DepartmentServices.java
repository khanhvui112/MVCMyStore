package com.sanvui.service;

import com.sanvui.model.entity.Department;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 05/11/2021-10:12 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class DepartmentServices {

    /*
    * find All Department
    * return List Department
    * */
    private static  final  BaseServices services = new BaseServices(Department.class);
    public List<Department> findAll(){
        List<Department> departmentList = services.findAll();
        System.out.println(departmentList);
        return departmentList;
    }

    /*
    * Insert department
    * */
    public boolean insert(List<Department> department){
        return services.insert(department);
    }

    /*
     * Update department
     * */
    public boolean update(List<Department> department){
        return services.update(department);
    }

    /*
     * Delete by Id department
     * */
    public boolean delete(int id){
        return services.delete(id);
    }
}
