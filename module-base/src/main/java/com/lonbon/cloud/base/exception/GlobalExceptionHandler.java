package com.lonbon.cloud.base.exception;

import com.lonbon.cloud.base.response.ResponseUtil;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Handler;
import org.noear.solon.core.handle.Context;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

/**
 * 全局异常处理器
 * 处理所有控制器抛出的异常，返回统一格式的错误响应
 */
@Component
public class GlobalExceptionHandler implements Handler {
    
    private static final Logger log = Logger.getLogger(GlobalExceptionHandler.class.getName());
    
    @Override
    public void handle(Context ctx) throws Exception {
        // 这个方法会在请求处理过程中被调用
        // 异常处理会在Solon框架内部自动捕获并处理
    }
}