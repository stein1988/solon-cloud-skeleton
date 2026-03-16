package com.lonbon.cloud.base.solon;

import java.util.Set;

public interface JdbcTypeHandlerReplaceConfigurer {

    boolean replace();

    Set<Class<?>> allowTypes();
}
