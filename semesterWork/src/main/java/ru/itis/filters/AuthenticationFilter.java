package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/signIn", "/signUp", "/main", "/files", "/users"})

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        Boolean isAuthenticated = false;
        Boolean sessionExists = session != null;
        Boolean isLoginPage = request.getRequestURI().equals("/signIn");
        Boolean isSignUpPage = request.getRequestURI().equals("/signUp");

        if (sessionExists) {
            isAuthenticated = (Boolean) session.getAttribute("authenticated");

            if (isAuthenticated == null) {
                isAuthenticated = false;
            }
        }

        if (isAuthenticated && (!isLoginPage && !isSignUpPage) || !isAuthenticated && isLoginPage || !isAuthenticated && isSignUpPage) {
            filterChain.doFilter(request, response);
        } else if (isAuthenticated) {
            response.sendRedirect("/main");
        } else {
            response.sendRedirect("/signIn");
        }

    }

    @Override
    public void destroy() {

    }
}


