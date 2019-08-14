package com.xueyou.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public MyWebAppInitializer() {
        System.out.println("=======================MyWebAppInitializer========================");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { SpringMvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    // 每个过滤器都会根据其具体类型添加一个默认名称，并自动映射到DispatcherServlet
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] { new CharacterEncodingFilter() };
    }
}
