# Entity Proxy Annotation Skill

## 技能名称
Entity Proxy Annotation

## 技能描述
当生成或修改Entity类时，确保为所有Entity类添加@EntityProxy注解，以支持Easy-Query ORM框架的代理功能。

## 应用场景
- 生成新的Entity类时
- 修改现有的Entity类时
- 为Entity类添加Easy-Query ORM支持时

## 技术要求
1. **注解导入**：确保在Entity类中导入`com.easy.query.core.annotation.EntityProxy`注解
2. **注解位置**：在类定义上方添加@EntityProxy注解
3. **注解参数**：无特殊参数要求，直接使用`@EntityProxy`即可

## 示例代码
```java
package com.lonbon.cloud.user.domain.entity;

import com.easy.query.core.annotation.Column;
import com.easy.query.core.annotation.EntityProxy;
import com.easy.query.core.annotation.Table;
import com.lonbon.cloud.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "lb_tenant")
@EntityProxy
public class Tenant extends BaseEntity {
    @Column(value = "name")
    private String name;
    
    @Column(value = "description")
    private String description;
    
    @Column(value = "is_default")
    private boolean default;
    
    @Column(value = "is_active")
    private boolean active;
    
    @Column(value = "domain")
    private String domain;
    
    @Column(value = "config")
    private String config;
    
    @Column(value = "is_system")
    private boolean system;
}
```

## 注意事项
- @EntityProxy注解是Easy-Query ORM框架的核心注解，用于生成实体类的代理对象
- 所有需要通过Easy-Query进行CRUD操作的实体类都必须添加此注解
- 注解位置必须在类定义上方，通常与@Table注解一起使用
- 确保导入正确的注解包路径，避免编译错误

## 验证方法
1. 检查生成的Entity类是否包含@EntityProxy注解
2. 编译项目，确保无编译错误
3. 运行Easy-Query相关测试，验证实体类能否正常被框架处理
