package com.skladApp.skladApp.Controllers;

import com.skladApp.skladApp.Configs.SecurityConfig;
import com.skladApp.skladApp.Models.UserModel;
import com.skladApp.skladApp.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authentication")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final SecurityConfig config;

    @PostMapping("/login")
    public Boolean login(@RequestParam String login, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } catch (AuthenticationException e) {
            return false;
        }
    }

    @PostMapping("/register")
    public void register(@RequestBody UserModel user) {
        user.setPassword(config.passwordEncoder().encode(user.getPassword()));
        userService.addUser(user);
    }
}
