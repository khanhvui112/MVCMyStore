package com.sanvui.repository;

import com.sanvui.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author: VuiSK
 * @created: 23/11/2021-10:13 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByUserNameAndPassword(String userName, String password);
    Employee findByUserName(String userName);
    Employee findByEmail(String email);
    Employee findByPhone(String phone);
    Employee findByTokenActive(String token);
}
