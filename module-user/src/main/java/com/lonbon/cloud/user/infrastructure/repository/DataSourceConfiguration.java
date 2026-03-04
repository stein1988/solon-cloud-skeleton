package com.lonbon.cloud.user.infrastructure.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean(name = "db_default", typed = true)
    public DataSource defaultDataSource(@Inject("${solon.dataSources.db_default}") HikariDataSource dataSource){
        return dataSource;
    }
}

