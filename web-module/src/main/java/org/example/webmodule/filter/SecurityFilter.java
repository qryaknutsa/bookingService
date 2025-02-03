package org.example.webmodule.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.businessmodule.exception.tools.CustomErrorResponse;

import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String method = httpRequest.getMethod();
        String path = httpRequest.getRequestURI(); // или getServletPath() в зависимости от нужд

        boolean isAuthorized = false;


        if (method.equals("GET")) {
            isAuthorized = httpRequest.isUserInRole("user") || httpRequest.isUserInRole("admin"); // GET для user и admin
        } else if (method.equals("POST") || method.equals("PUT") || method.equals("DELETE")) {
            isAuthorized = httpRequest.isUserInRole("admin"); // POST, PUT, DELETE только для admin
        }


        if (isAuthorized) {
            chain.doFilter(request, response);
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.setContentType("application/json");

            CustomErrorResponse errorResponse = new CustomErrorResponse(
                    "Ошибка авторизации",
                    "Недостаточно прав для выполнения этого действия",
                    ((HttpServletRequest) request).getRequestURL().toString()
            );

            String jsonResponse = new ObjectMapper().writeValueAsString(errorResponse);
            httpResponse.getWriter().write(jsonResponse);
        }
    }

}