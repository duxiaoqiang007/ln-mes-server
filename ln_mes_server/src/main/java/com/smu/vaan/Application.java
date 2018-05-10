package com.smu.vaan;

import com.smu.vaan.config.PrimaryDatabaseConfig;
import com.smu.vaan.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;


/**
 * Created by vaan on 16/6/24.
 * 应用全局入口
 */
@Import({WebSecurityConfig.class, PrimaryDatabaseConfig.class})
@SpringBootApplication
@PropertySource("classpath:config/config.yml")
public class Application extends SpringBootServletInitializer {

    private static final String MAX_REQUEST_SIZE = "10MB";



    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(Application.class);
    }

    @Bean
    public SimpleUrlHandlerMapping faviconHandlerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Integer.MIN_VALUE);
        mapping.setUrlMap(Collections.singletonMap("favicon.ico",
                faviconRequestHandler()));
        return mapping;
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainerFactory() {
        TomcatEmbeddedServletContainerFactory factory
                = new TomcatEmbeddedServletContainerFactory();
        factory.setPort(8080);
        return factory;
    }



//    /**
//     * 获取RestAdapter单例Bean
//     * @return WechatApi
//     */
//    @Bean
//    public WechatApi getWechatApi() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(WechatConfig.API_URL)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(JacksonConverterFactory.create())
//                .build();
//
//        return retrofit.create(WechatApi.class);
//
//    }

    @Bean
    protected ResourceHttpRequestHandler faviconRequestHandler() {
        ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
        requestHandler.setLocations(Arrays
                .<Resource> asList(new ClassPathResource("/")));
        return requestHandler;
    }

    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "datasource.primary")
//    @PropertySource("classpath:config/config.yml")
    public DataSource primaryDataSource(){

//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//        dataSource.setUrl(databaseProperties.getUrl());
//        dataSource.setUsername(databaseProperties.getUsername());
//        dataSource.setPassword(databaseProperties.getPassword());
//        dataSource.setInitialSize(3);
//        dataSource.setMaxActive(300);
//        dataSource.setMaxIdle(2);
//        dataSource.setMinIdle(1);
//        return dataSource;
        // oracle
        return DataSourceBuilder.create().build();
    }

//    @Bean(name = "secondaryDataSource")
//    @ConfigurationProperties(prefix = "datasource.secondary", locations = "classpath:config/config.yml")
//    public DataSource secondaryDataSource(){
//        return DataSourceBuilder.create().build();
//    }


    @Bean
    public MultipartConfigElement multipartConfigElement() {
        // Setup the application container to be accept multipart requests
        final MultipartConfigFactory factory = new MultipartConfigFactory();
        // Place upper bounds on the size of the requests to ensure that
        // clients don't abuse the web container by sending huge requests
        factory.setMaxFileSize(MAX_REQUEST_SIZE);
        factory.setMaxRequestSize(MAX_REQUEST_SIZE);
        // Return the configuration to setup multipart in the container
        return factory.createMultipartConfig();
    }


    @Bean
    public MultipartResolver multipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        //        multipartResolver.(10 * 1024 * 1024);
        return new StandardServletMultipartResolver();
    }











    public static void main(String args[]) {
        SpringApplication.run(Application.class,args);
    }
}
