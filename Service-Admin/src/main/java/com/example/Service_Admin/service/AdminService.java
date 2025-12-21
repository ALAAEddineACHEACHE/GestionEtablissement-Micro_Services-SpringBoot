package com.example.Service_Admin.service;

import com.example.Service_Admin.models.Establishment;
import com.example.Service_Admin.repo.EstablishmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final EstablishmentRepository repository;

    public AdminService(EstablishmentRepository repository) {
        this.repository = repository;
    }

    public Establishment save(Establishment e) {
        return repository.save(e);
    }
}
