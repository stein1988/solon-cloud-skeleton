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
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import java.util.List;

/**
 * Easy-Query 框架配置类
 * 用于注册和配置 Easy-Query 框架的各种组件
 * 包括拦截器、逻辑删除策略、分片初始化、列加密、版本策略等
 */
@Configuration
public class EasyQueryConfiguration {

    /**
     * 数据源配置（当前暂时注释掉，因为直接注入会导致报错）
     * TODO: 在这里注入，不会生效，查询时报错"Failed to get tenants: dataSource or dataSourceClassName or jdbcUrl is required."，原因未知
     */
//    @Bean(name = "db_default", typed = true)
//    public DataSource defaultDataSource(@Inject("${solon.dataSources.db_default}") HikariDataSource dataSource){
//        return dataSource;
//    }

    /**
     * 应用 Easy-Query 配置
     * 注册各种扩展组件到 Easy-Query 框架中
     * 
     * @param runtimeContext 查询运行时上下文
     * @param interceptorList 拦截器列表
     * @param logicDeleteStrategyList 逻辑删除策略列表
     * @param shardingInitializerList 分片初始化器列表
     * @param encryptionStrategyList 加密策略列表
     * @param versionStrategyList 版本策略列表
     * @param valueConverterList 值转换器列表
     * @param columnValueSQLConverterList 列值SQL转换器列表
     * @param generatedKeySQLColumnGeneratorList 生成键SQL列生成器列表
     * @param navigateExtraFilterStrategyList 导航额外过滤策略列表
     * @param navigateValueSetterList 导航值设置器列表
     * @param primaryKeyGeneratorList 主键生成器列表
     * @param tableRouteList 表路由列表
     * @param dataSourceRouteList 数据源路由列表
     * @param jdbcTypeHandlerConfigurerList JDBC类型处理器配置器列表
     */
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

        // 获取查询配置
        QueryConfiguration configuration = runtimeContext.getQueryConfiguration();

        // 注册拦截器
        for (Interceptor interceptor : interceptorList) {
            configuration.applyInterceptor(interceptor);
        }

        // 注册逻辑删除策略
        for (LogicDeleteStrategy logicDeleteStrategy : logicDeleteStrategyList) {
            configuration.applyLogicDeleteStrategy(logicDeleteStrategy);
        }

        // 注册分片初始化器
        for (ShardingInitializer shardingInitializer : shardingInitializerList) {
            configuration.applyShardingInitializer(shardingInitializer);
        }

        // 注册加密策略
        for (EncryptionStrategy encryptionStrategy : encryptionStrategyList) {
            configuration.applyEncryptionStrategy(encryptionStrategy);
        }

        // 注册版本策略
        for (VersionStrategy versionStrategy : versionStrategyList) {
            configuration.applyEasyVersionStrategy(versionStrategy);
        }

        // 注册值转换器
        for (ValueConverter<?,?> valueConverter : valueConverterList) {
            configuration.applyValueConverter(valueConverter);
        }

        // 注册列值SQL转换器
        for (ColumnValueSQLConverter columnValueSQLConverter : columnValueSQLConverterList) {
            configuration.applyColumnValueSQLConverter(columnValueSQLConverter);
        }

        // 注册生成键SQL列生成器
        for (GeneratedKeySQLColumnGenerator generatedKeySQLColumnGenerator : generatedKeySQLColumnGeneratorList) {
            configuration.applyGeneratedKeySQLColumnGenerator(generatedKeySQLColumnGenerator);
        }

        // 注册导航额外过滤策略
        for (NavigateExtraFilterStrategy navigateExtraFilterStrategy : navigateExtraFilterStrategyList) {
            configuration.applyNavigateExtraFilterStrategy(navigateExtraFilterStrategy);
        }

        // 注册导航值设置器
        for (NavigateValueSetter navigateValueSetter : navigateValueSetterList) {
            configuration.applyNavigateValueSetter(navigateValueSetter);
        }

        // 注册主键生成器
        for (PrimaryKeyGenerator primaryKeyGenerator : primaryKeyGeneratorList) {
            configuration.applyPrimaryKeyGenerator(primaryKeyGenerator);
        }

        // 注册表路由
        TableRouteManager tableRouteManager = runtimeContext.getTableRouteManager();
        for (TableRoute<?> tableRoute : tableRouteList) {
            tableRouteManager.addRoute(tableRoute);
        }

        // 注册数据源路由
        DataSourceRouteManager dataSourceRouteManager = runtimeContext.getDataSourceRouteManager();
        for (DataSourceRoute<?> dataSourceRoute : dataSourceRouteList) {
            dataSourceRouteManager.addRoute(dataSourceRoute);
        }

        // 注册JDBC类型处理器
        JdbcTypeHandlerManager jdbcTypeHandlerManager = runtimeContext.getJdbcTypeHandlerManager();
        for (JdbcTypeHandlerConfigurer jdbcTypeHandlerConfigurer : jdbcTypeHandlerConfigurerList) {
            jdbcTypeHandlerManager.appendHandler(jdbcTypeHandlerConfigurer.getType(), jdbcTypeHandlerConfigurer, true);
        }
    }
}
