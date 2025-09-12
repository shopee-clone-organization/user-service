package com.shopeeclone.service.impl;

//package com.shopeeclone.user.service.impl;

import com.shopeeclone.dto.*;
import com.shopeeclone.model.User;
import com.shopeeclone.repository.UserRepository;
import com.shopeeclone.service.UserService;
import com.shopeeclone.util.JwtUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public UserServiceImpl(UserRepository repo, JwtUtils jwtUtils) {
        this.repo = repo;
        this.jwtUtils = jwtUtils;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserResponse register(UserRegisterRequest request) {
        if (repo.existsByUsername(request.getUsername()) || repo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already exists");
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();
        repo.save(user);
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    @Override
    public String login(UserLoginRequest request) {
        User user = repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if(!encoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtils.generateToken(user.getUsername());
    }

    @Override
    public UserResponse getCurrentUser(String token) {
        String username = jwtUtils.extractUsername(token);
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}
