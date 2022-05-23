package se.callista.blog.management.service;

import java.util.UUID;

public interface TenantManagementService {
    
    void createTenant(UUID tenantId);

}