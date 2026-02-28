package com.lonbon.cloud.user.infrastructure.repository;

import com.easy.query.core.configuration.QueryConfiguration;
import com.easy.query.core.context.QueryRuntimeContext;
import com.easy.query.solon.annotation.Db;
import com.lonbon.cloud.base.config.BaseAutoConfiguration;
import com.zaxxer.hikari.HikariDataSource;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean(name = "db_master", typed = true)
    public DataSource masterDataSource(@Inject("${solon.dataSources.db_master}") HikariDataSource dataSource){
        return dataSource;
    }

    @Bean
    public void dbMasterQueryConfiguration(@Db("db_master") QueryConfiguration configuration){
        BaseAutoConfiguration.applyQueryConfiguration(configuration);
    }

    @Bean
    public void dbMasterQueryRuntimeContext(@Db("db_master") QueryRuntimeContext runtimeContext){
        BaseAutoConfiguration.applyRuntimeContext(runtimeContext);
    }
}
