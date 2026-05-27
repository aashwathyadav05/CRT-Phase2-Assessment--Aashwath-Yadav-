package org.example.secureloginapi.security;

import org.example.secureloginapi.user.Role;

public record JwtUser(String username, Role role) {
}
