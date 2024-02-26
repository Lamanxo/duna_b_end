package com.duna.dunaback.service;

import com.duna.dunaback.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.duna.dunaback.entities_mini.Role;

@Service
@RequiredArgsConstructor

public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }

    public Role getAdminRole() {
        return roleRepository.findByName("ROLE_ADMIN").get();
    }
}
