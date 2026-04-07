package com.gl.userservice.service;

import com.gl.userservice.dto.*;
import com.gl.userservice.entity.Role;
import com.gl.userservice.entity.User;
import com.gl.userservice.exception.ResourceNotFoundException;
import com.gl.userservice.exception.UserAlreadyExistsException;
import com.gl.userservice.repository.UserRepository;
import com.gl.userservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public String register(RegisterRequestDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered");
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new UserAlreadyExistsException("Phone already registered");
        }

        Role role;
        try {
            role = Role.valueOf(request.getRole().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Invalid role. Allowed roles: USER, WORKER");
        }

        if (role == Role.ADMIN) {
            throw new RuntimeException("ADMIN role cannot be registered via API");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(role)
                .address(request.getAddress())
                .walletBalance(0.0)
                .verified(role != Role.WORKER)
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    public AuthResponseDto login(LoginRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(userDetails);

        return new AuthResponseDto(token, "Login successful");
    }

    public UserResponseDto getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return mapToDto(user);
    }

    public UserResponseDto updateProfile(String email, RegisterRequestDto request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!user.getPhone().equals(request.getPhone()) && userRepository.existsByPhone(request.getPhone())) {
            throw new UserAlreadyExistsException("Phone already registered");
        }

        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());

        userRepository.save(user);

        return mapToDto(user);
    }

    public List<UserResponseDto> getPendingWorkers() {
        return userRepository.findByRoleAndVerified(Role.WORKER, false)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public UserResponseDto approveWorker(Long userId, WorkerApprovalRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Worker not found"));

        if (user.getRole() != Role.WORKER) {
            throw new RuntimeException("User is not a worker");
        }

        user.setVerified(request.getVerified());
        userRepository.save(user);

        return mapToDto(user);
    }

    private UserResponseDto mapToDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole().name())
                .address(user.getAddress())
                .walletBalance(user.getWalletBalance())
                .verified(user.getVerified())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}