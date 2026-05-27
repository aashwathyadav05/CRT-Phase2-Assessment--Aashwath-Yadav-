package org.example.secureloginapi.admin;

import java.security.Principal;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public Map<String, String> dashboard(Principal principal) {
        return Map.of(
                "message", "Welcome to the admin dashboard",
                "username", principal.getName());
    }
}
