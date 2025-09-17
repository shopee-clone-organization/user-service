package com.shopeeclone.controller;

//package com.shopeeclone.user.controller;

import com.shopeeclone.dto.*;
import com.shopeeclone.model.User;
import com.shopeeclone.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        try {
            UserResponse response = userService.login(request);
            return ResponseEntity.ok(response); // Gateway sẽ lấy info này để generate JWT
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRegisterRequest user) {
        UserResponse saved = userService.register(user);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMe(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        // Token ở đây không cần validate, chỉ chứa username do Gateway đã validate
        return ResponseEntity.ok(userService.getCurrentUser(token));
    }
}