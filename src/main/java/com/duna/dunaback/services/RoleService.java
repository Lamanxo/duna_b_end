package com.duna.dunaback.services;

import com.duna.dunaback.entities.Role;
import com.duna.dunaback.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    public Role getUserRole() {
        return roleRepo.findByName("ROLE_USER").get();
    }
}
