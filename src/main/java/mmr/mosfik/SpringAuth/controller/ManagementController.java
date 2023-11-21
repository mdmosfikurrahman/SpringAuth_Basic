package mmr.mosfik.SpringAuth.controller;

import mmr.mosfik.SpringAuth.service.management.ManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/management")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
public class ManagementController {

    private final ManagementService managementService;

    @Autowired
    public ManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'management:read')")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(managementService.get());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin:create', 'management:create')")
    public ResponseEntity<?> post() {
        return ResponseEntity.ok(managementService.post());
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin:update', 'management:update')")
    public ResponseEntity<?> put() {
        return ResponseEntity.ok(managementService.put());
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('admin:delete', 'management:delete')")
    public ResponseEntity<?> delete() {
        return ResponseEntity.ok(managementService.delete());
    }
}

