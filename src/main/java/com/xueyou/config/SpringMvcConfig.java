package com.xueyou.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Spring MVC 配置
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.xueyou.controller", useDefaultFilters = false)
public class SpringMvcConfig implements WebMvcConfigurer {

    public SpringMvcConfig() {
        System.out.println("======================xx=======================");
    }

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


    /**
     * 配置消息转换
     * @param converters 转换器列表
     */
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);//保留空的字段
        // SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
        // SerializerFeature.WriteNullNumberAsZero//Number null -> 0
        // 按需配置，更多参考FastJson文档哈
    	// 全局指定了日期格式
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        converters.add(converter);
    }

    /**
     * 配置静态资源
     * @param registry 静态资源注册
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/public", "classpath:/static/")
                .setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
    }

    /**
     * 配置默认Servlet
     * @param configurer 默认Servlet配置
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 上传文件解析
     *
     * @return
     */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver muliMultipartResolver = new CommonsMultipartResolver();
        muliMultipartResolver.setMaxUploadSize(4194304);
        muliMultipartResolver.setDefaultEncoding("UTF-8");
        return muliMultipartResolver;
    }


}
