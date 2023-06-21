package br.com.delivery.deliveryapi.controller;


import br.com.delivery.deliveryapi.dto.AuthenticationResponse;
import br.com.delivery.deliveryapi.dto.LoginRequest;
import br.com.delivery.deliveryapi.model.Usuario;
import br.com.delivery.deliveryapi.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api/v1/auth")
@Tag(name = "Auth", description = "API de Autentificação")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
        this.authService.signup(registerRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            AuthenticationResponse response = authService.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getuser")
    public Optional<Usuario> getUserByUsername(@RequestParam String username) {
        return this.authService.getUserByUsername(username);
    }

}

