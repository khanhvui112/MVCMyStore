package com.sanvui.config;

import com.sanvui.service.CustomerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
*
 * @author: VuiSK
 * @created: 27/11/2021-9:58 AM
 * @mailto: sanvankhanh@gmail.com

*/

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomerUserService customerUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        page only role admin
        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasAnyAuthority("ADMIN");

//      page for admin or manager
        http.authorizeRequests()
                .antMatchers("/admin-dashboard/**")
                .hasAnyAuthority("ADMIN","MANAGER");

//
        http.authorizeRequests()
                .and().exceptionHandling().accessDeniedPage("/accessDenied");


//        page not login passes
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
                .csrf().disable();

//        config login form
        http.authorizeRequests()
                .and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/home/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/home/login")
                .usernameParameter("username")
                .passwordParameter("password")
//                config logout
                .and().logout().logoutUrl("/home/logout")
                .logoutSuccessUrl("/home/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
