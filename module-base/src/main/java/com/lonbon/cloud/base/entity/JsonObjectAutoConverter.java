package com.lonbon.cloud.base.entity;

import com.easy.query.core.basic.extension.conversion.ValueAutoConverter;
import com.easy.query.core.metadata.ColumnMetadata;
import com.easy.query.core.util.EasyClassUtil;
import com.easy.query.core.util.EasyMapUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.noear.snack4.ONode;
import org.noear.solon.annotation.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JsonObjectAutoConverter implements ValueAutoConverter<Object, Object> {

    private static final Map<ColumnMetadata, Type> cacheMap = new ConcurrentHashMap<>();

    @Override
    public boolean apply(@NotNull Class<?> entityClass, @NotNull Class<Object> propertyType) {
        return JsonObject.class.isAssignableFrom(propertyType);
    }

    @Override
    public @Nullable Object serialize(@Nullable Object o, @NotNull ColumnMetadata columnMetadata) {
        return ONode.serialize(o);
    }

    @Override
    public @Nullable Object deserialize(@Nullable Object s, @NotNull ColumnMetadata columnMetadata) {
        return ONode.deserialize(getValueString(s), getFiledType(columnMetadata));
    }

    private @Nullable String getValueString(@Nullable Object s) {
        return switch (s) {
            case null -> null;
            case String string -> string;
            default -> s.toString();     // 这里包括了PGobject jsonb类型的处理
        };
    }

    private Type getFiledType(ColumnMetadata columnMetadata) {
        return EasyMapUtil.computeIfAbsent(cacheMap, columnMetadata, this::getFiledType0);
    }

    private Type getFiledType0(ColumnMetadata columnMetadata) {
        Class<?> entityClass = columnMetadata.getEntityMetadata().getEntityClass();
        Field declaredField = EasyClassUtil.getFieldByName(entityClass, columnMetadata.getPropertyName());
        return declaredField.getGenericType();
    }


}
