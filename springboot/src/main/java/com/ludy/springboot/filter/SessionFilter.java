package com.ludy.springboot.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SessionFilter implements Filter {
    private FilterConfig config;

    public void destroy() {
        this.config = null;
    }

    public void init(FilterConfig filterConfig) {
        config = filterConfig;
    }

    /**
     * 过滤器
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("hello world");
        HttpServletRequest hrequest = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Access-Control-Allow-Origin", hrequest.getHeader("Origin")); //解决跨域访问报错
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "Accept, Origin, X-Requested-With, Content-Type, Last-Modified, funid");
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        String logonStrings = config.getInitParameter("logonStrings");        // 登录登陆页面
        String includeStrings = config.getInitParameter("includeStrings");    // 过滤资源后缀参数
//        String disabletestfilter = config.getInitParameter("disabletestfilter");// 过滤器是否有效
//
//        // 过滤无效的请求
//        if (disabletestfilter.toUpperCase().equals("Y")) {
//            chain.doFilter(request, response);
//            return;
//        }
        String[] logonList = logonStrings.split(";");
        String[] includeList = includeStrings.split(";");
        chain.doFilter(request, response);//放行
//        return;
    }
}
