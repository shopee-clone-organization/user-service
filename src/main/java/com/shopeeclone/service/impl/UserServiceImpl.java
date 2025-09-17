package com.shopeeclone.service.impl;

//package com.shopeeclone.user.service.impl;

import com.shopeeclone.dto.*;
import com.shopeeclone.model.User;
import com.shopeeclone.repository.UserRepository;
import com.shopeeclone.service.UserService;
import com.shopeeclone.util.JwtUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
   
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserResponse register(UserRegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRoles());
        User saved = userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setUsername(saved.getUsername());
        response.setRoles(saved.getRoles());
        return response;
    }

    @Override
    public UserResponse login(UserLoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        UserResponse response = new UserResponse();
        response.setUsername(user.getUsername());
        response.setRoles(user.getRoles());
        return response;
    }

    @Override
    public UserResponse getCurrentUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse response = new UserResponse();
        response.setUsername(user.getUsername());
        response.setRoles(user.getRoles());
        return response;
    }
}
