package br.com.delivery.deliveryapi.controller;

import br.com.delivery.deliveryapi.model.Usuario;
import br.com.delivery.deliveryapi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "API para gerenciamento de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo usuário",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Usuario.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida")
            })
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.cadastrarUsuario(usuario);
    }

    @GetMapping("/{username}")
    @Operation(summary = "Obter um usuário pelo username",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Usuario.class))),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            })
    public Usuario obterUsuarioPorUsername(@PathVariable String username) {
        return usuarioService.obterUsuarioPorUsername(username);
    }

    @PostMapping("/verificar-senha")
    @Operation(summary = "Verificar senha",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Senha verificada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida")
            })
    public boolean verificarSenha(@RequestBody Usuario usuario, @RequestParam String senha) {
        return usuarioService.verificarSenha(usuario, senha);
    }
}
