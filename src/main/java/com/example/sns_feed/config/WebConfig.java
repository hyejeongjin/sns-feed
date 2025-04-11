package com.example.sns_feed.config;

import com.example.sns_feed.config.filter.LoginFilter;
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

}
