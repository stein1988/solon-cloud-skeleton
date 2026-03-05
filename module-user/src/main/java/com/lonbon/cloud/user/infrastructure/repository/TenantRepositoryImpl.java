package com.lonbon.cloud.user.infrastructure.repository;

import com.easy.query.api.proxy.client.EasyEntityQuery;
import com.easy.query.solon.annotation.Db;
import com.lonbon.cloud.base.repository.EasyQueryRepository;
import com.lonbon.cloud.user.domain.entity.Tenant;
import com.lonbon.cloud.user.domain.entity.proxy.TenantProxy;
import com.lonbon.cloud.user.domain.repository.TenantRepository;
import org.noear.solon.annotation.Component;

import java.util.UUID;

@Component
public class TenantRepositoryImpl extends EasyQueryRepository<TenantProxy, Tenant, TenantProxy.TenantProxyFetcher, UUID> implements TenantRepository {
    public TenantRepositoryImpl(@Db EasyEntityQuery easyEntityQuery) {
        super(easyEntityQuery, Tenant.class, proxy -> proxy.FETCHER);
    }
}
