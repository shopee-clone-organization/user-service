package com.shopeeclone.service;

//package com.shopeeclone.user.service;

import com.shopeeclone.dto.UserRegisterRequest;
import com.shopeeclone.dto.UserLoginRequest;
import com.shopeeclone.dto.UserResponse;

public interface UserService {
    UserResponse register(UserRegisterRequest request);
    String login(UserLoginRequest request);
    UserResponse getCurrentUser(String token);
}
