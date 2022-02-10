package org.ua.uesf.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.ua.uesf.config.jwt.JwtProvider;
import org.ua.uesf.model.User;
import org.ua.uesf.model.dto.user.AuthRequest;
import org.ua.uesf.model.dto.user.AuthResponse;
import org.ua.uesf.model.dto.user.RegistrationDto;
import org.ua.uesf.service.auth.AuthService;
import org.ua.uesf.service.user.UserService;

import javax.validation.Valid;

@AllArgsConstructor

@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse registerUser(@RequestBody @Valid RegistrationDto registrationDto) {
        return authService.register(registrationDto);
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        return authService.auth(request);
    }

    @PostMapping("/auth2")
    public String auth2(@AuthenticationPrincipal User user) {

        return user.getUserName();
    }
}
