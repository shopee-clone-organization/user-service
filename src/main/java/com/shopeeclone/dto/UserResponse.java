package com.shopeeclone.dto;

import java.util.List;

//package com.shopeeclone.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private List<String> roles;
}