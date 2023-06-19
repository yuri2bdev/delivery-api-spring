package br.com.delivery.deliveryapi.controller;

import br.com.delivery.deliveryapi.model.Cliente;
import br.com.delivery.deliveryapi.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "API para gerenciamento de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @Operation(summary = "Lista todos os clientes cadastrados")
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um cliente pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Cliente.class))),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
            })
    public Cliente obterClientePorId(@PathVariable Long id) {
        return clienteService.obterClientePorId(id);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo cliente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Cliente.class)))
            })
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        return clienteService.cadastrarCliente(cliente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um cliente pelo ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Cliente atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
            })
    public void atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        clienteService.atualizarCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um cliente pelo ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
            })
    public void deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
    }
}
