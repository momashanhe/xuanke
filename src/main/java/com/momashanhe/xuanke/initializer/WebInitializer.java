package com.momashanhe.xuanke.initializer;

import com.momashanhe.xuanke.config.RootConfig;
import com.momashanhe.xuanke.config.WebConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    // 指定根容器配置类，在Spring容器中创建，具有唯一性
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    // 指定Web容器配置类，在MVC容器中创建，可以有多个
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    // 指定映射路径
    @Override
    protected String[] getServletMappings() {
        // 设置DispatcherServlet的映射路径
        return new String[]{"/"};
    }

    // 指定过滤器
    @Override
    protected Filter[] getServletFilters() {
        // 注册字符编码过滤器
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return new Filter[]{encodingFilter};
    }
}
