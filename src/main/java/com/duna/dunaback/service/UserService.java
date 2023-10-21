package com.duna.dunaback.service;

import com.duna.dunaback.dtos.RegistrationUserDto;
import com.duna.dunaback.dtos.UserDtoOut;
import com.duna.dunaback.exceptions.registration.PhoneAlreadyExistException;
import com.duna.dunaback.exceptions.registration.UsernameAlreadyExistException;
import com.duna.dunaback.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.duna.dunaback.entities.User;
import com.duna.dunaback.exceptions.registration.EmailAlreadyExistException;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private UserRepo userRepo;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(()-> new UsernameNotFoundException("User with id " + id + "not found"));
    }

    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User with name " + username + "not found"));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public UserDtoOut createNewUser(RegistrationUserDto dto) {
        newUserValidation(dto);
        User user = makeUser(dto);
        log.warn("New user {}, {}, {}", user.getUsername(),user.getEmail(), user.getPhone());
        return makeUserDtoOut(userRepo.save(user));
    }

    private UserDtoOut makeUserDtoOut(User user) {
        return new UserDtoOut(user.getId(), user.getUsername(), user.getPhone(), user.getEmail());
    }

    private User makeUser(RegistrationUserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Set.of(roleService.getUserRole()));
        return user;
    }

    private void newUserValidation(RegistrationUserDto dto) {
        if (userRepo.findByUsername(dto.getUsername()).isPresent())
            throw new UsernameAlreadyExistException("Username " + dto.getUsername() + " already taken");
        else if (userRepo.findByEmail(dto.getEmail()).isPresent())
            throw new EmailAlreadyExistException("Email " + dto.getEmail() + " already taken");
        else if (userRepo.findByPhone(dto.getPhone()).isPresent())
            throw new PhoneAlreadyExistException("Phone " + dto.getPhone() + " already registered");
    }
}
