package com.lonbon.cloud.user.application.dto;

import com.lonbon.cloud.user.domain.entity.Tenant;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.util.UUID;

@AutoMapper(target = Tenant.class)
@Data
public class TenantDto {
    private UUID id;
    private String name;
}
