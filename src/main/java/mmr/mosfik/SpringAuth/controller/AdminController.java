package mmr.mosfik.SpringAuth.controller;

import lombok.RequiredArgsConstructor;
import mmr.mosfik.SpringAuth.service.admin.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(adminService.get());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<String> post() {
        return new ResponseEntity<>(adminService.post(), OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<String> put() {
        return new ResponseEntity<>(adminService.put(), OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<String> delete() {
        return new ResponseEntity<>(adminService.delete(), OK);
    }
}

