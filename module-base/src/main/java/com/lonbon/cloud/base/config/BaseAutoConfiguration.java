package com.lonbon.cloud.base.config;

import com.easy.query.core.basic.jdbc.types.JdbcTypeHandlerManager;
import com.easy.query.core.configuration.QueryConfiguration;
import com.easy.query.core.context.QueryRuntimeContext;
import com.lonbon.cloud.base.entity.DefaultEntityInterceptor;
import com.lonbon.cloud.base.entity.DefaultLogicDeleteStrategy;
import com.lonbon.cloud.base.entity.OffsetDateTimeTypeHandler;
import com.lonbon.cloud.base.entity.UUIDPrimaryKeyGenerator;
import org.noear.solon.annotation.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

/**
 * 基础模块自动配置类
 * 用于扫描 base 模块的组件，使其能被上层模块自动注入
 */
@Component
public class BaseAutoConfiguration {

    public static void applyQueryConfiguration(QueryConfiguration configuration) {
        configuration.applyInterceptor(new DefaultEntityInterceptor());
        configuration.applyLogicDeleteStrategy(new DefaultLogicDeleteStrategy());
        configuration.applyPrimaryKeyGenerator(new UUIDPrimaryKeyGenerator());
    }

    public static void applyRuntimeContext(QueryRuntimeContext runtimeContext) {
        JdbcTypeHandlerManager jdbcTypeHandlerManager = runtimeContext.getJdbcTypeHandlerManager();
        jdbcTypeHandlerManager.appendHandler(OffsetDateTime.class,OffsetDateTimeTypeHandler.INSTANCE,true);
    }
}
