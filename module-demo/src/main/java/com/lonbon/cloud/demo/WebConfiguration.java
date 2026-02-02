package com.lonbon.demo.core;

import com.zaxxer.hikari.HikariDataSource;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import javax.sql.DataSource;

@Configuration
public class WebConfiguration {
    @Bean(name = "db_master", typed = true)
    public DataSource masterDataSource(@Inject("${solon.dataSources.db_master}") HikariDataSource dataSource){
        return dataSource;
    }
}