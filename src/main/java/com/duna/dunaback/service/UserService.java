package com.duna.dunaback.service;

import com.duna.dunaback.dtos.RegistrationUserDto;
import com.duna.dunaback.dtos.UserDtoOut;
import com.duna.dunaback.entities.EmailToken;
import com.duna.dunaback.exceptions.authreg.*;
import com.duna.dunaback.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.duna.dunaback.entities.User;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {


    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final EmailTokenService emailTokenService;
    private final EmailSenderService emailSenderService;


    //test_____________
    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(()-> new UsernameNotFoundException("User with id " + id + "not found"));
    }
    //test_____________
    public User getUserByPhone(String phone) {
        return userRepo.findByPhone(phone).orElseThrow(()->
                new PhoneNotFoundException("Phone number " + phone + " not found"));
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(()->
                new EmailNotFoundException("User with email " + email + " not found"));
    }
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User with name " + username + "not found"));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByEmail(username);
        if(!user.isVerified())
            throw new UserNotVerifiedException("User: " + user.getUsername() + " is not verified");
        if(!user.isActive())
            throw new UserIsBlockedException("User: " + user.getUsername() + " is blocked");

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public UserDtoOut createNewUser(RegistrationUserDto dto) {
        newUserValidation(dto);
        User user = userRepo.save(makeUser(dto));
        String token = UUID.randomUUID().toString();
        EmailToken emailToken = new EmailToken(token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(30),user);
        emailTokenService.saveEmailToken(emailToken);
        emailSenderService.sendVerificationEmail(user.getEmail(), token);
        log.warn("New user {}, {}, {}", user.getUsername(),user.getEmail(), user.getPhone());
        return makeUserDtoOut(user);
    }

    public String userEmailVerification(String token) {
        EmailToken emailToken = emailTokenService.getEmailTokenByToken(token);
        if(emailToken.getConfirmedAt() != null) {
            throw new UserAlreadyVerificatedException("User: " + emailToken.getUser().getUsername()
                    + " already verificated");
        }
        if(LocalDateTime.now().isBefore(emailToken.getExpiresAt())) {
            throw new EmailTokenExpiredException("Email token lifetime expired");
        }
        emailToken.setConfirmedAt(LocalDateTime.now());
        User user = emailToken.getUser();
        user.setActive(true);
        user.setVerified(true);
        userRepo.save(user);
        return "User's " + user.getUsername() + " verification complete";
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
