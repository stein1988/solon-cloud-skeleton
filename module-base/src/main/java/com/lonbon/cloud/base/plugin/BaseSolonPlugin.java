package com.lonbon.cloud.base.plugin;

import com.lonbon.cloud.base.config.EasyQueryConfiguration;
import com.lonbon.cloud.base.config.SerializerConfiguration;
import com.lonbon.cloud.base.config.UUIDConverter;
import com.lonbon.cloud.base.entity.DefaultEntityInterceptor;
import com.lonbon.cloud.base.entity.DefaultLogicDeleteStrategy;
import com.lonbon.cloud.base.entity.OffsetDateTimeTypeHandler;
import com.lonbon.cloud.base.entity.UUIDPrimaryKeyGenerator;
import com.lonbon.cloud.base.exception.ExceptionFilter;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * 基础模块 Solon 插件
 * 用于在应用启动时自动注册基础模块的各种公共组件
 * 实现了 Solon 的 Plugin 接口，参与应用生命周期
 */
public class BaseSolonPlugin implements Plugin {
    
    /**
     * 插件启动方法
     * 在应用启动时被调用，注册各种公共组件
     * 
     * @param context 应用上下文
     * @throws Throwable 启动过程中可能发生的异常
     */
    @Override
    public void start(AppContext context) throws Throwable {
        // 注册序列化配置组件
        context.beanMake(SerializerConfiguration.class);
        
        // 注册全局异常过滤器
        context.beanMake(ExceptionFilter.class);
        
        // 注册 UUID 类型转换器
        context.beanMake(UUIDConverter.class);

        // 注册实体拦截器
        context.beanMake(DefaultEntityInterceptor.class);
        
        // 注册逻辑删除策略
        context.beanMake(DefaultLogicDeleteStrategy.class);
        
        // 注册 OffsetDateTime 类型处理器
        context.beanMake(OffsetDateTimeTypeHandler.class);
        
        // 注册 UUID 主键生成器
        context.beanMake(UUIDPrimaryKeyGenerator.class);
        
        // 注册 Easy-Query 框架配置
        context.beanMake(EasyQueryConfiguration.class);
    }
}
