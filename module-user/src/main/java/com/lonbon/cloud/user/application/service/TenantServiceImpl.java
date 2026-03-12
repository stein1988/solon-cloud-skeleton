package com.lonbon.cloud.user.application.service;

import com.lonbon.cloud.user.application.dto.TenantDto;
import com.lonbon.cloud.user.domain.dto.TenantCreateDTO;
import com.lonbon.cloud.user.domain.entity.Tenant;
import com.lonbon.cloud.user.domain.repository.TenantRepository;
import com.lonbon.cloud.user.domain.service.TenantService;
import io.github.linpeilie.Converter;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TenantServiceImpl implements TenantService {

    @Inject
    private TenantRepository tenantRepository;

    @Inject
    private Converter converter;

    @Override
    public Tenant createTenant(TenantCreateDTO tenant) {
        Tenant createTenant = converter.convert(tenant,Tenant.class);
        return tenantRepository.save(createTenant);
    }

    @Override
    public Tenant updateTenant(TenantDto tenant) {
        Optional<Tenant> exists = tenantRepository.findById(tenant.getId());
        if (exists.isPresent()) {
            Tenant update = converter.convert(tenant, exists.get());
            return tenantRepository.save(update);
        } else throw new RuntimeException("not exists");
    }

    @Override
    public void deleteTenant(UUID id) {
        tenantRepository.deleteById(id);
    }

    @Override
    public Optional<Tenant> getTenantById(UUID id) {
        return tenantRepository.findById(id);
    }

    @Override
    public Optional<Tenant> getTenantByName(String name) {
//        return tenantRepository.findByName(name);
        return Optional.empty();
    }

    @Override
    public List<Tenant> getAllTenants() {
        return (List<Tenant>) tenantRepository.findAll();
    }

    @Override
    public Optional<Tenant> getDefaultTenant() {
//        return tenantRepository.findDefaultTenant();
        return Optional.empty();
    }

    @Override
    public void setDefaultTenant(UUID tenantId) {
//        // 先将所有租户的isDefault设置为false
//        List<Tenant> tenants = tenantRepository.findAll();
//        for (Tenant tenant : tenants) {
//            tenant.setDefault(tenant.getId().equals(tenantId));
//            tenantRepository.save(tenant);
//        }
    }
}
