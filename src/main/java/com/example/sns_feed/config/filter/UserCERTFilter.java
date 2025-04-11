//package com.example.sns_feed.config.filter;
//
//import com.example.sns_feed.common.exception.CustomException;
//import com.example.sns_feed.common.exception.ErrorCode;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.util.PatternMatchUtils;
//
//import java.io.IOException;
//
//public class UserCERTFilter implements Filter {
//
//
//    private static final String[] CERT_URL_LIST = {"/check"};
//
//    @Override
//    public void doFilter(ServletRequest request,
//                         ServletResponse response,
//                         FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String requestURI = httpRequest.getRequestURI();
//
//        if (isCERT_URL_LIST(requestURI)) {
//            System.out.println(requestURI); //check
//            HttpSession httpSession = httpRequest.getSession(false);
//
//            // 이거 하나만을 위해서 Filter로 빠지는게 더 안좋다
//            if (httpSession == null || httpSession.getAttribute("cert") == null) {
//                throw new CustomException(ErrorCode.INVALID_SESSION);
//            }
//
//        }
//        chain.doFilter(request, response);
//    }
//
//    private boolean isCERT_URL_LIST(String requestURI) {
//        return PatternMatchUtils.simpleMatch(CERT_URL_LIST, requestURI);
//    }
//
//}
