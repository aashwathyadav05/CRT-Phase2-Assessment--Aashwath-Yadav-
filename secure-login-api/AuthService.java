package org.example.secureloginapi.auth;

import org.example.secureloginapi.security.JwtService;
import org.example.secureloginapi.user.AppUser;
import org.example.secureloginapi.user.Role;
import org.example.secureloginapi.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponse register(RegisterRequest request) {
        validateCredentials(request.username(), request.password());
        if (userRepository.existsByUsername(request.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        Role role = request.role() == null ? Role.USER : request.role();
        AppUser savedUser = userRepository.save(new AppUser(
                request.username(),
                passwordEncoder.encode(request.password()),
                role));

        return new UserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getRole());
    }

    public AuthResponse login(LoginRequest request) {
        AppUser user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        return new AuthResponse(jwtService.generateToken(user), "Bearer", jwtService.getExpirationSeconds());
    }

    private void validateCredentials(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.length() < 6) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Username is required and password must be at least 6 characters");
        }
    }
}
