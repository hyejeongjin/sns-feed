package com.example.sns_feed.config;

import com.example.sns_feed.config.filter.ChangePasswordFilter;
import com.example.sns_feed.config.filter.LoginFilter;
import com.example.sns_feed.config.filter.UserCERTFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter()); // Filter 등록
        filterRegistrationBean.setOrder(1); // Filter 순서
        filterRegistrationBean.addUrlPatterns("/*"); // 전체 URL에 Filter 적용

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean UserCERTFilter() {
        FilterRegistrationBean<UserCERTFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new UserCERTFilter()); // Filter 등록
        filterRegistrationBean.setOrder(2); // Filter 순서
        filterRegistrationBean.addUrlPatterns("/*"); // 전체 URL에 Filter 적용

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean ChangePasswordFilter() {
        FilterRegistrationBean<ChangePasswordFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new ChangePasswordFilter()); // Filter 등록
        filterRegistrationBean.setOrder(3); // Filter 순서
        filterRegistrationBean.addUrlPatterns("/*"); // 전체 URL에 Filter 적용

        return filterRegistrationBean;
    }
}
