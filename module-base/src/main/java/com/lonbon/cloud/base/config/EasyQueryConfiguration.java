package com.lonbon.cloud.base.config;

import com.easy.query.core.basic.extension.conversion.ColumnValueSQLConverter;
import com.easy.query.core.basic.extension.conversion.ValueConverter;
import com.easy.query.core.basic.extension.encryption.EncryptionStrategy;
import com.easy.query.core.basic.extension.generated.GeneratedKeySQLColumnGenerator;
import com.easy.query.core.basic.extension.generated.PrimaryKeyGenerator;
import com.easy.query.core.basic.extension.interceptor.Interceptor;
import com.easy.query.core.basic.extension.logicdel.LogicDeleteStrategy;
import com.easy.query.core.basic.extension.navigate.NavigateExtraFilterStrategy;
import com.easy.query.core.basic.extension.navigate.NavigateValueSetter;
import com.easy.query.core.basic.extension.version.VersionStrategy;
import com.easy.query.core.basic.jdbc.types.JdbcTypeHandlerManager;
import com.easy.query.core.configuration.QueryConfiguration;
import com.easy.query.core.context.QueryRuntimeContext;
import com.easy.query.core.sharding.initializer.ShardingInitializer;
import com.easy.query.core.sharding.route.datasource.DataSourceRoute;
import com.easy.query.core.sharding.route.table.TableRoute;
import com.easy.query.core.sharding.router.manager.DataSourceRouteManager;
import com.easy.query.core.sharding.router.manager.TableRouteManager;
import com.easy.query.solon.annotation.Db;
import com.lonbon.cloud.base.entity.JdbcTypeHandlerConfigurer;
import com.zaxxer.hikari.HikariDataSource;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class EasyQueryConfiguration {

    // TODO: 在这里注入，不会生效，查询时报错"Failed to get tenants: dataSource or dataSourceClassName or jdbcUrl is required."，原因未知
//    @Bean(name = "db_default", typed = true)
//    public DataSource defaultDataSource(@Inject("${solon.dataSources.db_default}") HikariDataSource dataSource){
//        return dataSource;
//    }

    @Bean
    public void applyQueryConfiguration(@Db QueryRuntimeContext runtimeContext,
                                        @Inject List<Interceptor> interceptorList,
                                        @Inject List<LogicDeleteStrategy> logicDeleteStrategyList,
                                        @Inject List<ShardingInitializer> shardingInitializerList,
                                        @Inject List<EncryptionStrategy> encryptionStrategyList,
                                        @Inject List<VersionStrategy> versionStrategyList,
                                        @Inject List<ValueConverter<?,?>> valueConverterList,
                                        @Inject List<ColumnValueSQLConverter> columnValueSQLConverterList,
                                        @Inject List<GeneratedKeySQLColumnGenerator> generatedKeySQLColumnGeneratorList,
                                        @Inject List<NavigateExtraFilterStrategy> navigateExtraFilterStrategyList,
                                        @Inject List<NavigateValueSetter> navigateValueSetterList,
                                        @Inject List<PrimaryKeyGenerator> primaryKeyGeneratorList,
                                        @Inject List<TableRoute<?>> tableRouteList,
                                        @Inject List<DataSourceRoute<?>> dataSourceRouteList,
                                        @Inject List<JdbcTypeHandlerConfigurer> jdbcTypeHandlerConfigurerList
    ) {

        QueryConfiguration configuration = runtimeContext.getQueryConfiguration();

        //拦截器注册
        for (Interceptor interceptor : interceptorList) {
            configuration.applyInterceptor(interceptor);
        }

        //逻辑删除
        for (LogicDeleteStrategy logicDeleteStrategy : logicDeleteStrategyList) {
            configuration.applyLogicDeleteStrategy(logicDeleteStrategy);
        }

        //分片初始化
        for (ShardingInitializer shardingInitializer : shardingInitializerList) {
            configuration.applyShardingInitializer(shardingInitializer);
        }

        //列加密
        for (EncryptionStrategy encryptionStrategy : encryptionStrategyList) {
            configuration.applyEncryptionStrategy(encryptionStrategy);
        }

        //数据行版本
        for (VersionStrategy versionStrategy : versionStrategyList) {
            configuration.applyEasyVersionStrategy(versionStrategy);
        }

        for (ValueConverter<?,?> valueConverter : valueConverterList) {
            configuration.applyValueConverter(valueConverter);
        }

        for (ColumnValueSQLConverter columnValueSQLConverter : columnValueSQLConverterList) {
            configuration.applyColumnValueSQLConverter(columnValueSQLConverter);
        }

        for (GeneratedKeySQLColumnGenerator generatedKeySQLColumnGenerator : generatedKeySQLColumnGeneratorList) {
            configuration.applyGeneratedKeySQLColumnGenerator(generatedKeySQLColumnGenerator);
        }


        for (NavigateExtraFilterStrategy navigateExtraFilterStrategy : navigateExtraFilterStrategyList) {
            configuration.applyNavigateExtraFilterStrategy(navigateExtraFilterStrategy);
        }

        for (NavigateValueSetter navigateValueSetter : navigateValueSetterList) {
            configuration.applyNavigateValueSetter(navigateValueSetter);
        }


        for (PrimaryKeyGenerator primaryKeyGenerator : primaryKeyGeneratorList) {
            configuration.applyPrimaryKeyGenerator(primaryKeyGenerator);
        }

        TableRouteManager tableRouteManager = runtimeContext.getTableRouteManager();
        for (TableRoute<?> tableRoute : tableRouteList) {
            tableRouteManager.addRoute(tableRoute);
        }


        DataSourceRouteManager dataSourceRouteManager = runtimeContext.getDataSourceRouteManager();
        for (DataSourceRoute<?> dataSourceRoute : dataSourceRouteList) {
            dataSourceRouteManager.addRoute(dataSourceRoute);
        }

        JdbcTypeHandlerManager jdbcTypeHandlerManager = runtimeContext.getJdbcTypeHandlerManager();
        for (JdbcTypeHandlerConfigurer jdbcTypeHandlerConfigurer : jdbcTypeHandlerConfigurerList) {
            jdbcTypeHandlerManager.appendHandler(jdbcTypeHandlerConfigurer.getType(), jdbcTypeHandlerConfigurer, true);
        }
    }
}
