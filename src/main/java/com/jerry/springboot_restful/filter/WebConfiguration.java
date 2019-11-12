package com.jerry.springboot_restful.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jerry.springboot_restful.config.IpConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class WebConfiguration {
    @Autowired
    private IpConfig ipConfig;
    @Bean
    public FilterRegistrationBean testFilterRegistration()
    {
        FilterRegistrationBean registration=new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/getUser","/hello");
        registration.addInitParameter("paramName","paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }

    class MyFilter implements Filter
    {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            System.out.println("init: "+filterConfig);
        }

        @Override
        public void destroy() {
            System.out.println("destroy");
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request=(HttpServletRequest) servletRequest;
            HttpServletResponse response=(HttpServletResponse)servletResponse;
            if(!checkIP(request,response))
            {
                return;
            }
            filterChain.doFilter(request,response);
        }
    }
    private boolean checkIP(HttpServletRequest request,HttpServletResponse response)
    {
        String ip=getIpAddr(request);
        //获取系统白名单
        String ipStr=ipConfig.getIpWhiteList();
        if(ipStr.contains(ip))
        {
            System.out.println("IP 通过 "+ip);
            return true;
        }else
        {
            System.out.println("IP 不通过 "+ip);
            try {
                Map<String,Object> messageMap=new HashMap<>();
                messageMap.put("status","1");
                messageMap.put("messgae","ip :"+ip+"没有权限");
                ObjectMapper objectMapper=new ObjectMapper();
                String write=objectMapper.writeValueAsString(messageMap);
                response.getWriter().write(write);

            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return false;
        }
    }
    private String getIpAddr(HttpServletRequest request)
    {
        String ip=request.getHeader("X-Forward-For");
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip))
        {
            ip=request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
