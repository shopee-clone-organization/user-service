package com.shopeeclone.service;

import com.shopeeclone.dto.UserRegisterRequest;
import com.shopeeclone.dto.UserLoginRequest;
import com.shopeeclone.dto.UserResponse;
import com.shopeeclone.model.User;

public interface UserService {

    UserResponse register(UserRegisterRequest request);

    // Xác thực login, trả thông tin user để Gateway tạo JWT
    UserResponse login(UserLoginRequest request);

    // Lấy thông tin user dựa trên username hoặc token (Gateway đã validate)
    UserResponse getCurrentUser(String username);
}
