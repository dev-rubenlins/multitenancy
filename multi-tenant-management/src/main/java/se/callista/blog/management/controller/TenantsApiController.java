package se.callista.blog.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.callista.blog.management.service.TenantManagementService;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class TenantsApiController {

    @Autowired
    private TenantManagementService tenantManagementService;

    @PostMapping("/tenants")
    public ResponseEntity<Void> createTenant(@RequestParam @NotBlank String tenantId) {
        UUID tenantUUID = UUID.fromString(tenantId);
        tenantManagementService.createTenant(tenantUUID);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
