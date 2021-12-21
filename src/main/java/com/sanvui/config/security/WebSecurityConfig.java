package com.sanvui.config.security;

import com.sanvui.service.imp.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author: VuiSK
 * @created: 10/12/2021-3:39 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        page only for admin
        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasAnyAuthority("ADMIN");

//      page only for admin or manager
        http.authorizeRequests()
                .antMatchers("/admin-dashboard/**")
                .hasAnyAuthority("ADMIN", "MANAGER");

//        request access denied
        http.authorizeRequests()
                .and().exceptionHandling().accessDeniedPage("/accessDenied");


//        Config page public
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
                .csrf().disable();


//        config login form
        http.authorizeRequests().
                antMatchers("/login")
                .permitAll().anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().logout().logoutSuccessUrl("/login?logout-success")
                .deleteCookies("access_token").invalidateHttpSession(true);

        http.authorizeRequests()
                .and()
                .apply(getSecurityConfig());
    }

    private JwtConfig getSecurityConfig() {
        return new JwtConfig();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder);
    }

}
