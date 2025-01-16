package org.example.webmodule.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.wildfly.security.http.oidc.OidcPrincipal;

import java.io.IOException;
import java.security.Principal;

@WebFilter("/*")
public class SecurityFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Principal userPrincipal = httpRequest.getUserPrincipal();

        if (userPrincipal instanceof OidcPrincipal) {
            OidcPrincipal oidcPrincipal = (OidcPrincipal) userPrincipal;
            String token = oidcPrincipal.getOidcSecurityContext().getTokenString();

            // Сохраняем токен в JNDI-ресурс
            System.out.println("Token saved in JNDI: " + token);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}