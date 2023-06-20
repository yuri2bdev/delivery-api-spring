package br.com.delivery.deliveryapi.controller;

import br.com.delivery.deliveryapi.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "API para autenticação de usuarios")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário e gerar token JWT")
    public ResponseEntity<String> login(
            @Parameter(description = "Nome de usuário") @RequestParam String username,
            @Parameter(description = "Senha do usuário") @RequestParam String password) {
        String token = authService.authenticate(username, password);
        return ResponseEntity.ok(token);
    }
}
