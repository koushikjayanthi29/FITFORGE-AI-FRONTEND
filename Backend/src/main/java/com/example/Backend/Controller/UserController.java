package com.example.Backend.Controller;

import org.springframework.security.core.Authentication;
import com.example.Backend.Entity.User;
import com.example.Backend.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        User registeredUser = userService.registerUser(user);

        return ResponseEntity.ok(registeredUser);
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication authentication) {

        return ResponseEntity.ok(
                "JWT authentication successful. Logged in user: "
                + authentication.getName()
        );
    }
}