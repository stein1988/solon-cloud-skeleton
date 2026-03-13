package com.lonbon.cloud.base.entity;

import com.easy.query.core.basic.jdbc.executor.internal.merge.result.StreamResultSet;
import com.easy.query.core.basic.jdbc.executor.internal.props.JdbcProperty;
import com.easy.query.core.basic.jdbc.parameter.SQLParameter;
import com.easy.query.core.basic.jdbc.types.EasyParameter;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.noear.solon.annotation.Component;
import org.postgresql.util.PGobject;

import java.sql.JDBCType;
import java.sql.SQLException;

@Slf4j
@Component
public class JsonObjectTypeHandler implements JdbcTypeHandlerConfigurer{
    @Override
    public @NonNull Class<?> getType() {
        return String.class;
    }

    @Override
    public Object getValue(JdbcProperty jdbcProperty, StreamResultSet streamResultSet) throws SQLException {
        return streamResultSet.getString(jdbcProperty.getJdbcIndex());
    }

    @Override
    public void setParameter(EasyParameter parameter) throws SQLException {
        SQLParameter sqlParameter = parameter.getSQLParameter();
        JDBCType jdbcType = sqlParameter.getJdbcType();
        if (jdbcType == JDBCType.OTHER) {
            Class<?> propertyType = sqlParameter.getColumnMetadata().getPropertyType();
            if (JsonObject.class.isAssignableFrom(propertyType)) {
                // TODO：还要判断当前的数据库类型做特殊处理
                PGobject pGobject = new PGobject();
                pGobject.setType("jsonb");
                pGobject.setValue((String) parameter.getValue());
                parameter.getPs().setObject(parameter.getIndex(), pGobject);
                return;
            }
        }

        parameter.getPs().setString(parameter.getIndex(), (String)parameter.getValue());
    }
}
