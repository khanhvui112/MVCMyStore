package com.sanvui.sercurity;


import com.sanvui.dto.LoginDTO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @author: VuiSK
 * @created: 14/11/2021-8:54 PM
 * @mailto: sanvankhanh@gmail.com
 */

public class LogFilterAdmin implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter Admin statrt!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String servletPath = req.getServletPath();

        System.out.println("#INFO Admin Employee" + new Date() + " - ServletPath :" + servletPath //
                + ", URL =" + req.getRequestURL());

        HttpSession session = req.getSession();
        LoginDTO loginDTO = (LoginDTO) session.getAttribute("loginDTO");
        // Cho phép request được đi tiếp. (Vượt qua Filter này).

        if (loginDTO != null && loginDTO.getRole().equalsIgnoreCase("ADMIN")) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect("/Home/login");
        }


    }

    @Override
    public void destroy() {
        System.out.println("Filter Admin end!");
    }
}
