package com.example.sns_feed.config.filter;

import com.example.sns_feed.common.exception.CustomException;
import com.example.sns_feed.common.exception.ErrorCode;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class ChangePasswordFilter implements Filter {

    private static final String[] CHANGE_URL_LIST = {"/", "/change_password"};

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        if (isCHANGE_URL_LIST(requestURI)) {
            System.out.println(requestURI); //check
            HttpSession httpSession = httpRequest.getSession(false);

            if (httpSession == null || httpSession.getAttribute("goNewPassword") == null) {
                throw new CustomException(ErrorCode.INVALID_SESSION);
            }


        }
        chain.doFilter(request, response);
    }

    private boolean isCHANGE_URL_LIST(String requestURI) {
        return PatternMatchUtils.simpleMatch(CHANGE_URL_LIST, requestURI);
    }

}
