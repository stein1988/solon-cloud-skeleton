package com.lonbon.cloud.demo;

import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;
import org.noear.solon.core.handle.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class SolonAuthInterceptor implements Filter {
    private static final Logger log = LoggerFactory.getLogger(SolonAuthInterceptor.class);

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        String path = ctx.path();
        log.info("filter {}", path);
        // 核心：放行Knife4j所有路径，直接返回true不拦截
//        if (path.startsWith("/doc.html")
//                || path.startsWith("/webjars/")
//                || path.startsWith("/v3/api-docs/")
//                || path.startsWith("/openapi/")
//                || path.startsWith("/knife4j/")) {
//
//        }
        // 你的其他权限拦截逻辑（如登录校验、角色校验）
        chain.doFilter(ctx);
    }
}
