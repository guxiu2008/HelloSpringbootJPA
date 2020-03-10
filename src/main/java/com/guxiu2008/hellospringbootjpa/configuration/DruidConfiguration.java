package com.guxiu2008.hellospringbootjpa.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Package: com.guxiu2008.hellospringbootjpa.configuration
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2020-02-22 16:13
 **/
@Slf4j
@Configuration
public class DruidConfiguration {

    @Value("${spring.druid.exceptionSorter}")
    private String exceptionSorter;

    @Value("${spring.druid.filters}")
    private String filters;

    @Value("${spring.druid.password-callback}")
    private String passwordCallbackClassName;

    @Bean
    //@Primary
    // 扫描application.yml中的spring.datasource.primary并配置到DruidDataSource中
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
        log.info("Creating the primary DataBase connection pool...");
        //DruidDataSource dataSource = new DruidDataSource();
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        try {
            dataSource.setExceptionSorter(exceptionSorter);
            dataSource.setFilters(filters);
            dataSource.setPasswordCallbackClassName(passwordCallbackClassName);
        } catch (Exception e) {
            log.error("druid configuration initialization error", e);
        }
        return dataSource;
    }
}
