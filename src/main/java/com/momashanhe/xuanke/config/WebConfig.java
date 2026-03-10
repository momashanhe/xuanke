package com.momashanhe.xuanke.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan({"com.momashanhe.xuanke.controller", "com.momashanhe.xuanke.business"})
public class WebConfig implements WebMvcConfigurer {
    // 配置JSP视图解析器
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setContentType("text/html;charset=UTF-8");
        return resolver;
    }

    // 配置静态资源处理
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/favicon.ico");
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    // 配置消息转换器，设置编码格式
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 遍历消息转换器列表
        for (int i = 0; i < converters.size(); i++) {
            // 修改原始StringHttpMessageConverter转换器
            if (converters.get(i) instanceof StringHttpMessageConverter) {
                // 设置UTF-8编码
                StringHttpMessageConverter custom = new StringHttpMessageConverter(StandardCharsets.UTF_8);
                // 设置为false可以避免响应头过大
                custom.setWriteAcceptCharset(false);
                // 替换原始StringHttpMessageConverter转换器
                converters.set(i, custom);
                break;
            }
        }
    }
}