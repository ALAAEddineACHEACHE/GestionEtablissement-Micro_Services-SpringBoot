package com.example.Service_Admin.controller;

import com.example.Service_Admin.models.Establishment;
import com.example.Service_Admin.service.AdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping("/establishment")
    @PreAuthorize("hasRole('ADMIN')")
    public Establishment create(@RequestBody Establishment e) {
        return service.save(e);
    }
}

