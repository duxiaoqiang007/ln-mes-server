package com.smu.vaan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * Created by vaan on 2017/3/3.
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
    @Value("${upload-path}")
    private String uploadPath;

    @Value("${resources-path}")
    private String resourcesPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcesPath + "**")
                .addResourceLocations("file:" + uploadPath)
                .setCacheControl(CacheControl.maxAge(7200, TimeUnit.SECONDS));

        super.addResourceHandlers(registry);
    }
}
