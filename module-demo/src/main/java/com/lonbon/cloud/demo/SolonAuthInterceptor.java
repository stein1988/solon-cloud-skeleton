package com.lonbon.cloud.demo;

import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Handler;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.HandlerInterceptor;

@Component
public class SolonAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(Context ctx, Handler handler) throws Exception {
        String path = ctx.path();
        // 核心：放行Knife4j所有路径，直接返回true不拦截
        if (path.startsWith("/doc.html")
                || path.startsWith("/webjars/")
                || path.startsWith("/v3/api-docs/")
                || path.startsWith("/openapi/")
                || path.startsWith("/knife4j/")) {
            return true;
        }
        // 你的其他权限拦截逻辑（如登录校验、角色校验）
        return true;
    }
}
