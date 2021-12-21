package com.sanvui.config.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author: VuiSK
 * @created: 13/12/2021-10:42 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class JwtConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Override
    public void configure(HttpSecurity builder) throws Exception {
        builder.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
