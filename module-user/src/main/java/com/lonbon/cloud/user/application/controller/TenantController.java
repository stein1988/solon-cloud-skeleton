package com.lonbon.cloud.user.application.controller;

import com.lonbon.cloud.common.utils.Response;
import com.lonbon.cloud.user.domain.dto.TenantCreateDTO;
import com.lonbon.cloud.user.domain.dto.TenantUpdateDTO;
import com.lonbon.cloud.user.domain.entity.Tenant;
import com.lonbon.cloud.user.domain.service.TenantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.*;
import org.noear.solon.validation.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Slf4j
//@Tag(name = "租户")
@Api("租户")
@Valid
@Controller
@Mapping("/api/tenants")
public class TenantController {

    @Inject
    private TenantService tenantService;

//    @Operation(summary = "创建")
    @ApiOperation("创建")
    @Post
    @Mapping
    public Response<UUID> create(@Body @Validated @NotNull TenantCreateDTO tenant) {
        Tenant createdTenant = tenantService.createTenant(tenant);
        return Response.success(createdTenant.getId(), "Tenant created successfully");
    }

    @ApiOperation("删除")
    @Post
    @Mapping("/{id}/delete")
    public Response<UUID> delete(@Path("id") UUID id) {
        tenantService.deleteTenant(id);
        return Response.success(id, "Tenant deleted successfully");
    }

    @ApiOperation("更新")
    @Post
    @Mapping("/{id}/update")
    public Response<UUID> update(@Path("id") UUID id, @Body @Validated TenantUpdateDTO tenant) {
        tenantService.updateTenant(id, tenant);
        return Response.success(id, "Tenant updated successfully");
    }

    @ApiOperation("获取")
    @Get
    @Mapping("/{id}")
    public Response<Tenant> getTenantById(@Path("id") UUID id) {
        Optional<Tenant> tenant = tenantService.getTenantById(id);
        return tenant.map(Response::success).orElseGet(() -> Response.error("Tenant not found"));
    }

////    @Operation(summary = "查询单个（根据名称）")
//    @Get
//    @Mapping("/name/{name}")
//    public Response<Tenant> getTenantByName(@Path("name") String name) {
//        try {
//            Optional<Tenant> tenant = tenantService.getTenantByName(name);
//            return tenant.map(Response::success).orElseGet(() -> Response.error("Tenant not found"));
//        } catch (Exception e) {
//            return Response.error("Failed to get tenant: " + e.getMessage());
//        }
//    }
//
////    @Operation(summary = "查询所有")
//    @Get
//    @Mapping
//    public Response<List<Tenant>> getAllTenants() {
//        try {
////            Tenant newTenant = new Tenant();
////            newTenant.setName("测试租户");
////            tenantService.createTenant(newTenant);
//            List<Tenant> tenants = tenantService.getAllTenants();
//            for (Tenant tenant : tenants) {
//                log.info(tenant.toString());
//            }
//            return Response.success(tenants);
//        } catch (Exception e) {
//            return Response.error("Failed to get tenants: " + e.getMessage());
//        }
//    }
//
//    @Get
//    @Mapping("/default")
//    public Response<Tenant> getDefaultTenant() {
//        try {
//            Optional<Tenant> tenant = tenantService.getDefaultTenant();
//            return tenant.map(Response::success).orElseGet(() -> Response.error("Default tenant not found"));
//        } catch (Exception e) {
//            return Response.error("Failed to get default tenant: " + e.getMessage());
//        }
//    }
//
//    @Put
//    @Mapping("/default/{id}")
//    public Response<Void> setDefaultTenant(@Path("id") String id) {
//        try {
//            tenantService.setDefaultTenant(UUID.fromString(id));
//            return Response.success(null, "Default tenant set successfully");
//        } catch (Exception e) {
//            return Response.error("Failed to set default tenant: " + e.getMessage());
//        }
//    }
}
