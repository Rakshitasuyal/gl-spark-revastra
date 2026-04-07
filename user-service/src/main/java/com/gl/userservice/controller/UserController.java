package com.gl.userservice.controller;

import com.gl.userservice.dto.*;
import com.gl.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDto request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/users/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @GetMapping("/users/profile")
    public ResponseEntity<UserResponseDto> getProfile(Authentication authentication) {
        return ResponseEntity.ok(userService.getProfile(authentication.getName()));
    }

    @PutMapping("/users/profile")
    public ResponseEntity<UserResponseDto> updateProfile(Authentication authentication,
                                                         @Valid @RequestBody RegisterRequestDto request) {
        return ResponseEntity.ok(userService.updateProfile(authentication.getName(), request));
    }

    @GetMapping("/users/secured")
    public ResponseEntity<String> securedApi() {
        return ResponseEntity.ok("This is a secured API");
    }

    @GetMapping("/admin/workers/pending")
    public ResponseEntity<List<UserResponseDto>> getPendingWorkers() {
        return ResponseEntity.ok(userService.getPendingWorkers());
    }

    @PutMapping("/admin/workers/{userId}/approve")
    public ResponseEntity<UserResponseDto> approveWorker(
            @PathVariable Long userId,
            @RequestBody WorkerApprovalRequestDto request
    ) {
        return ResponseEntity.ok(userService.approveWorker(userId, request));
    }
}