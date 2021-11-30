package com.sanvui.service;

import com.sanvui.model.entity.Employee;
import com.sanvui.model.entity.EmployeeRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: VuiSK
 * @created: 27/11/2021-10:27 AM
 * @mailto: sanvankhanh@gmail.com
 */

@Service
public class CustomerUserService implements UserDetailsService {

    @Autowired
    EmployeeServices services;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Employee user = services.findByUserName(s);
        if (Objects.nonNull(user)){
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            GrantedAuthority authority = null;
            if(user.getEmployeeRoles()!= null){
                for (EmployeeRole emp : user.getEmployeeRoles()){
                    authority = new SimpleGrantedAuthority(emp.getRole().getRoleName());
                    grantedAuthorities.add(authority);
                }
            }
            return new User(user.getUserName(),user.getPassword(), grantedAuthorities);
        }else{
            throw new UsernameNotFoundException("Đăng nhập thất  bại!");
        }
    }
}
