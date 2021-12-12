package com.sanvui.service.imp;

import com.sanvui.model.entity.Employee;
import com.sanvui.service.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: VuiSK
 * @created: 11/12/2021-11:21 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    EmployeeServices services;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<Employee> user = services.findByUserName(s);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("The username doesn't exist:!");
        }

        Employee employee = user.get();
        List<GrantedAuthority> grantedAuthorities = employee.getEmployeeRoles()
                .stream().map(r->new SimpleGrantedAuthority(r.getRole().getRoleName()))
                .collect(Collectors.toList());

        return new User(employee.getUserName(), employee.getPassword(), grantedAuthorities);
    }

}
