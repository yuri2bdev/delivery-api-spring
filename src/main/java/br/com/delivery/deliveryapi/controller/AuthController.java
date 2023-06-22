package br.com.delivery.deliveryapi.controller;

import br.com.delivery.deliveryapi.dto.AuthenticationResponse;
import br.com.delivery.deliveryapi.dto.LoginRequest;
import br.com.delivery.deliveryapi.dto.RegisterRequest;
import br.com.delivery.deliveryapi.model.Usuario;
import br.com.delivery.deliveryapi.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Cria um novo usuário na aplicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
        this.authService.signup(registerRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Realiza a autentificação do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autentificação realizada com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
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
    @Operation(summary = "Busca um usuário pelo nome de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public Optional<Usuario> getUserByUsername(@RequestParam String username) {
        return this.authService.getUserByUsername(username);
    }
}