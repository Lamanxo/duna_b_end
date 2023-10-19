package com.duna.dunaback.services;

import com.duna.dunaback.dtos.RegistrationUserDto;
import com.duna.dunaback.entities.User;
import com.duna.dunaback.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsernameOptional(String username) {
        return userRepo.findByUsername(username);
    }

    public Optional<User> findByPhoneOptional(String phone) {
        return userRepo.findByPhone(phone);
    }

    public User findUserByPhone(String phone) {
        return userRepo.findByPhone(phone).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with phone '%s' not found", phone)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByPhone(username);
        return new org.springframework.security.core.userdetails.User(
                user.getPhone(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public User createUser(RegistrationUserDto newUser) {
        return userRepo.save(makeUser(newUser));
    }

    private User makeUser(RegistrationUserDto newUser) {
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setEmail(newUser.getEmail());
        user.setPhone(newUser.getPhone());
        user.setRoles(Set.of(roleService.getUserRole()));
        return user;
    }
}
