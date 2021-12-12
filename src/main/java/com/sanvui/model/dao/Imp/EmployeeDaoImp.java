package com.sanvui.model.dao.Imp;

import com.sanvui.model.dao.EmployeeDao;
import com.sanvui.model.dto.SalaryDTO;
import com.sanvui.model.entity.Department;
import com.sanvui.model.entity.Employee;
import com.sanvui.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 07/11/2021-12:44 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class EmployeeDaoImp implements EmployeeDao {

    /*
     * Query List  Employee salary
     * if insert success list SalaryDTOS
     * else return NULL
     * param: List<Employee>
     * request : true or false
     */
    @Override
    public List<SalaryDTO> salaryDtoList() {
        try (Session session = HibernateUtils.getSession()) {

//           create NativeQuery
            NativeQuery query = session.createNativeQuery(
                    "SELECT (emp.first_Name+' '+emp.last_Name) as full_Name,  s.start_Date, s.end_Date, s.salary, s.bonus " +
                            "FROM Salary s " +
                            "INNER JOIN  Employee emp " +
                            "ON emp.emp_Id = s.emp_Id " +
                            "INNER JOIN Department d " +
                            "ON emp.dep_Id = d.dep_Id " +
                            "WHERE d.dep_Name <> N'Người dùng' or d.dep_Name <> 'user'");

            List<Object[]> tuples = (List<Object[]>) query.getResultList();

            Iterator itr = tuples.iterator();
            List<SalaryDTO> salaryDTOS = new ArrayList<>();
            SalaryDTO salaryDTO;

//            add data to List salaryDTOS
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                //now you have one array of Object for each row
                String fullName = String.valueOf(obj[0]);
                String startDateStr = String.valueOf(obj[1]);
                LocalDate startDate = localDate(startDateStr);
                String endDateStr = String.valueOf(obj[2]);
                LocalDate endDate = localDate(endDateStr);
                double salary = Double.parseDouble(String.valueOf(obj[3]));
                double bonus = Double.parseDouble(String.valueOf(obj[4]));
                salaryDTO = new SalaryDTO();
                salaryDTO.setFullName(fullName);
                salaryDTO.setStartDate(startDate);
                salaryDTO.setEndDate(endDate);
                salaryDTO.setSalary(salary);
                salaryDTO.setBonus(bonus);
                salaryDTOS.add(salaryDTO);
            }
            return salaryDTOS;
        } catch (Exception e) {
            System.out.println("Get salaryDTO fail.");
            return null;
        }
    }

    @Override
    public List<Employee> findAll() {
        try (Session session = HibernateUtils.getSession()) {
            return session.createSQLQuery("SELECT * FROM Employee ").list();
        }
    }

    /*
     * Query List  Employee salary
     * if insert success list SalaryDTOS
     * else return NULL
     * param: List<Employee>
     * request : true or false
     */
    @Override
    public List<Employee> employeeList() {
        try (Session session = HibernateUtils.getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = query.from(Employee.class);
            Join<Employee, Department> join = root.join("department", JoinType.INNER);

//            predicate
            Predicate predicate = criteriaBuilder.notEqual(join.get("depName"), "Người dùng");
            Predicate predicate1 = criteriaBuilder.notEqual(join.get("depName"), "user");

            query.select(root).where(predicate, predicate1);
            Query query1 = session.createQuery(query);
            return query1.getResultList();
        }
    }

    /*
     * User register
     * if insert success return true
     * else return false
     * param: a employee
     * request : true or false
     */
    @Override
    public boolean insertUser(Employee employee) {
        try (Session session = HibernateUtils.getSession()) {
            Transaction transaction = session.beginTransaction();
            Query sql = session.createSQLQuery(
                    "INSERT INTO dbo.Employee(address,email,first_Name,last_Name,password,phone,user_Name,date_Of_Birth, gender )" +
                            "VALUES(? ,? , ?, ?, ?, ?, ?, ?, ?)"
            );
            sql.setParameter(1, employee.getAddress());
            sql.setParameter(2, employee.getEmail());
            sql.setParameter(3, employee.getFirstName());
            sql.setParameter(4, employee.getLastName());
            sql.setParameter(5, employee.getPassword());
            sql.setParameter(6, employee.getPhone());
            sql.setParameter(7, employee.getUserName());
            sql.setParameter(8, employee.getDateOfBirth());
            sql.setParameter(9, employee.getGender());
            int id = sql.executeUpdate();
            transaction.commit();
            return id > 0;
        }
    }

    @Override
    public boolean findEmployeeByName(String username) {
        try (Session session = HibernateUtils.getSession()) {
            Query query = session.createQuery(" SELECT empId FROM Employee " +
                    " WHERE userName =: username ");
            query.setParameter("username", username);
            return (Integer) query.getSingleResult() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean findEmployeeByEmail(String email) {
        try (Session session = HibernateUtils.getSession()) {
            Query query = session.createQuery(" SELECT empId FROM Employee " +
                    " WHERE email =: email ");
            query.setParameter("email", email);
            return (Integer) query.getSingleResult() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean findEmployeeByPhone(String phone) {
        try (Session session = HibernateUtils.getSession()) {
            Query query = session.createQuery(" SELECT empId FROM Employee " +
                    " WHERE phone =: phone ");
            query.setParameter("phone", phone);
            return (Integer) query.getSingleResult() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private static LocalDate localDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
        LocalDate date = LocalDate.parse(s, formatter);
        return date;
    }
}
