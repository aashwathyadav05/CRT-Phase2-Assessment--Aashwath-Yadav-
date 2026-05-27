package org.example.secureloginapi.auth;

import org.example.secureloginapi.user.Role;

public record RegisterRequest(String username, String password, Role role) {
}
