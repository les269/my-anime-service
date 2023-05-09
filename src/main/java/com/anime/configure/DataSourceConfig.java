package com.anime.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Autowired
    SqliteConfig sqliteConfig;
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource  dataSource  = new DriverManagerDataSource();
        dataSource.setDriverClassName(sqliteConfig.getDriverClassName());
        dataSource.setUrl(sqliteConfig.getUrl());
        return dataSource;
    }
}

