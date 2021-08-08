package com.imooc.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    /**
     * 这里注册一下url和对应的视图名称
     *
     *
     * 在controller中，直接返回模板路径
     *
     * 在url地址中，通过配置的路径和视图名称进行对应
     *
     * 在html中，使用的是springMVC 路由器名称
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login/login");

        registry.addViewController("/main").setViewName("login/main");
    }
}
