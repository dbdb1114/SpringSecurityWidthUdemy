package com.eazybytes.filter;

import jakarta.servlet.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.logging.Logger;

public class AuthoritiesLoggingAfterFilter implements Filter {

    private final Logger LOG = Logger.getLogger(AuthoritiesLoggingAfterFilter.class.getName());
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        /**
         * 인증처리가 되면 SecurityContext에 저장되므로, 가져올 수 있음.
         * null check는 인증처리를 확인하는 것.
         * */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(null != authentication){
            LOG.info("User" + authentication.getName() + " is successfully authenticated and "
            + "has the authorities " + authentication.getAuthorities().toString());
        }
        chain.doFilter(request, response);
    }
}
