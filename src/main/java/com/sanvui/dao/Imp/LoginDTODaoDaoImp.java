package com.sanvui.dao.Imp;

import com.sanvui.dao.LoginDTODao;
import com.sanvui.dto.LoginDTO;
import com.sanvui.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 11/11/2021-9:47 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class LoginDTODaoDaoImp implements LoginDTODao {
    @Override
    public LoginDTO getLoginDto(String username) {
        try (Session session = HibernateUtils.getSession()){
            //           create NativeQuery
            Query query = session.createSQLQuery(
                    "SELECT emp.user_Name, emp.password, r.role_Name, a.avatar_Name " +
                            " FROM  Role r" +
                            " FULL JOIN Employee emp" +
                            " ON emp.role_Code = r.role_Code " +
                            " FULL JOIN Avatar a" +
                            " ON a.emp_Id = emp.emp_Id" +
                            " WHERE emp.user_Name = ? ");

            query.setParameter(1, username);
            List<Object[]> tuples = (List<Object[]>) query.getResultList();

            Iterator itr = tuples.iterator();
            LoginDTO loginDTO = new LoginDTO();

//            add data to List salaryDTOS
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                //now you have one array of Object for each row
                String userName = String.valueOf(obj[0]);
                String password = String.valueOf(obj[1]);
                String roleName = String.valueOf(obj[2]);
                String avatarName = String.valueOf(obj[3]);
                loginDTO.setUserName(userName);
                loginDTO.setPassword(password);
                loginDTO.setRole(roleName);
                loginDTO.setAvatar(avatarName);
            }
            return loginDTO;
        }catch (Exception e) {
            return null;
        }
    }
}
