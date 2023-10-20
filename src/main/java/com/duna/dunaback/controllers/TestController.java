package com.duna.dunaback.controllers;

import com.duna.dunaback.dtos.RegistrationUserDtoOut;
import com.duna.dunaback.entities.Role;
import com.duna.dunaback.repo.RoleRepo;
import com.duna.dunaback.services.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    private final RoleRepo roleRepo;
    @GetMapping("/unsecured")
    public String unsecuredData() {
        return "Unsecured data";
    }

    @GetMapping("/secured")
    public String securedData() {
        return "Secured data";
    }

    @GetMapping("/admin")
    public String adminData() {
        return "Admin data";
    }

    @GetMapping("/info")
    public String userData(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/user/{id}")
    public RegistrationUserDtoOut getUserById(@PathVariable("id") Long id) {
        return testService.getUserById(id);
    }

    @GetMapping("/role/{id}")
    public Role getRoleById(@PathVariable("id") Long id) {
        return roleRepo.findById(id).get();
    }

}
