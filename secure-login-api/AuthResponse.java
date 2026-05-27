package org.example.secureloginapi.auth;

public record AuthResponse(String token, String tokenType, long expiresInSeconds) {
}
