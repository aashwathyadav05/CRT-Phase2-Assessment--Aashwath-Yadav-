package org.example.secureloginapi.auth;

import org.example.secureloginapi.user.Role;

public record UserResponse(Long id, String username, Role role) {
}
