package com.lonbon.cloud.base.exception;

import com.lonbon.cloud.base.response.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;
import org.noear.solon.core.util.DataThrowable;

import java.util.Objects;

@Slf4j
@Component
public class ExceptionFilter implements Filter{
    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        try {
            chain.doFilter(ctx);

//            if (!ctx.getHandled()) {
//                log.error("404 NOT FOUND: {}", ctx.path());
//                ctx.render(R.fail(404, "资源不存在"));
//            } else {
//                // 已经被solon处理了, 但是有些特殊情况, 需要在这里处理
//                // 判断这里的result 是否是 DataThrowable
//                if (ctx.result instanceof final DataThrowable dataThrowable) {
//                    // 如果是 DataThrowable, 且 dataThrowable 中的 data 为空, 则需要手动填充 response body
//                    final Object data = dataThrowable.data();
//                    if (Objects.isNull(data)) {
//                        ctx.render(R.fail(HttpStatus.ERROR, dataThrowable.getMessage()));
//                    }
//                }
//            }
//        } catch (SaTokenException e) {
//            ctx.render(R.fail(HttpStatus.FORBIDDEN, "你没有权限"));
//        } catch (ValidatorException e) {
//            ctx.render(R.fail(e.getCode(), e.getMessage()));
//        } catch (DataThrowable | ServiceException | DemoModeException e) {
//            ctx.render(R.fail(HttpStatus.ERROR, e.getMessage()));
//        } catch (EasyQuerySQLCommandException exp) {
//            Throwable cause = exp.getCause();
//            if (cause instanceof EasyQuerySQLStatementException) {
//                log.error("SQL语句异常: {}{}{} {}", System.lineSeparator(), ((EasyQuerySQLStatementException) cause).getSQL(), System.lineSeparator(), cause.getMessage(), exp);
//            } else {
//                log.error("异常信息:{}", exp.getMessage(), exp);
//            }
//            ctx.render(R.fail(HttpStatus.ERROR, "SQL语句异常"));
        } catch (Throwable e) {
            log.error("系统异常", e);
            ctx.render(ResponseUtil.error(500, e.getMessage()));
        }
    }
}
