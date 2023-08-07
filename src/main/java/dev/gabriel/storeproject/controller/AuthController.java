package dev.gabriel.storeproject.controller;

import dev.gabriel.storeproject.dto.EmailDTO;
import dev.gabriel.storeproject.security.UserSS;
import dev.gabriel.storeproject.service.AuthService;
import dev.gabriel.storeproject.service.UserService;
import dev.gabriel.storeproject.service.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    final JWTUtil jwtUtil;
    final AuthService service;

    @PostMapping("/refreshToken")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.authenticated();
        if (user == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot")
    public ResponseEntity<Void> forgotPassword(@Valid @RequestBody EmailDTO email) {
        service.sendNewPassword(email.email());
        return ResponseEntity.noContent().build();
    }
}
