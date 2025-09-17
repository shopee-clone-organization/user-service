package com.shopeeclone.dto;

import java.util.List;

//package com.shopeeclone.user.dto;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String username;
    private String password;
    private String email;
    private List<String> roles;
}