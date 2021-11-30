package com.sanvui.model.dao.Imp;

import com.sanvui.model.dao.DepartmentDao;
import com.sanvui.model.entity.Department;
import com.sanvui.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

/**
 * @author: VuiSK
 * @created: 05/11/2021-10:11 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class DepartmentDaoImp implements DepartmentDao {
    @Override
    public int findByName() {
        try (Session session = HibernateUtils.getSession()) {
            NativeQuery query = session.createNativeQuery(
                    "SELECT * " +
                            " FROM Department d" +
                            " WHERE d.dep_Name = N'Người dùng' or d.dep_Name = 'user' ").addEntity("department", Department.class);
            List<Department> result = query.getResultList();
            int id = 0;
            id = result.get(0).getDepId();
            return id;
        }
    }
}
