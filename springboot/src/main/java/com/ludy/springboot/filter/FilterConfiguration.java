package com.ludy.springboot.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
    public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(new SessionFilter());
        //拦截规则
        registration.addUrlPatterns("/*");
        registration.addInitParameter("logonStrings", "/index.jsp;getMenuList.wn;testDataSource.wn");
        registration.addInitParameter("includeStrings", ".wn");
        registration.addInitParameter("redirectPath", "/index.jsp");
        //过滤器名称
        registration.setName("SessionFilter");
        //是否自动注册 false 取消Filter的自动注册
//        registration.setEnabled(false);
        //过滤器顺序
        registration.setOrder(1);
        return registration;
    }
}
