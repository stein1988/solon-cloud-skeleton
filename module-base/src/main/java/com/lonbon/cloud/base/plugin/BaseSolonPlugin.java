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

public class BaseSolonPlugin implements Plugin {
    @Override
    public void start(AppContext context) throws Throwable {
        context.beanMake(SerializerConfiguration.class);
        context.beanMake(ExceptionFilter.class);
        context.beanMake(UUIDConverter.class);

        context.beanMake(DefaultEntityInterceptor.class);
        context.beanMake(DefaultLogicDeleteStrategy.class);
        context.beanMake(OffsetDateTimeTypeHandler.class);
        context.beanMake(UUIDPrimaryKeyGenerator.class);
        context.beanMake(EasyQueryConfiguration.class);
    }
}
