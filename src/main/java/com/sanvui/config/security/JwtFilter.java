package com.sanvui.config.security;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.sanvui.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: VuiSK
 * @created: 13/12/2021-10:45 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class JwtFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest servletRequest
            , ServletResponse servletResponse
            , FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest) servletRequest;

        Cookie[] cookies = request.getCookies();

        Cookie accessToken = getAccessCookie(cookies);

        String userName = null;
        if (Objects.nonNull(accessToken)) {
            JWSObject jWSObject = JwtUtil.decodeToken(accessToken.getValue());
            if (Objects.nonNull(jWSObject)) {
                Map<String, Object> payload = jWSObject.getPayload().toJSONObject();
                userName =  payload.get("userName").toString();
                JSONArray role = (JSONArray) payload.get("role");

                List<GrantedAuthority> authorityList = role.stream()
                        .map(r -> new SimpleGrantedAuthority(r.toString()))
                        .collect(Collectors.toList());

                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(userName, null, authorityList);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        HttpSession session = request.getSession();
        session.setAttribute("userName", userName);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Cookie getAccessCookie(Cookie[] cookies){
        if(Objects.nonNull(cookies)){
            for (Cookie c: cookies){
                if(c.getName().equalsIgnoreCase("access_token")){
                    return c;
                }
            }
        }
        return null;
    }


}
