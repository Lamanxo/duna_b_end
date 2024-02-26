package com.duna.dunaback.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duna.dunaback.entities_mini.Role;
import com.duna.dunaback.service.RoleService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final RoleService roleService;

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

    //Test
    @GetMapping("/role/user")
    public Role getUserRole() {
        return roleService.getUserRole();
    }

    //Test
    @GetMapping("/role/admin")
    public Role getAdminRole() {
        return roleService.getAdminRole();
    }
}