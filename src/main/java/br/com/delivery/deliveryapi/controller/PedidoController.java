package br.com.delivery.deliveryapi.controller;

import br.com.delivery.deliveryapi.model.Pedido;
import br.com.delivery.deliveryapi.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "API para gerenciamento de pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    @Operation(summary = "Lista todos os pedidos cadastrados")
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um pedido pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedido encontrado",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Pedido.class))),
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
            })
    public Pedido obterPedidoPorId(@PathVariable Long id) {
        return pedidoService.obterPedidoPorId(id);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo pedido",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedido cadastrado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Pedido.class)))
            })
    public Pedido cadastrarPedido(@RequestBody Pedido pedido) {
        return pedidoService.cadastrarPedido(pedido);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um pedido pelo ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Pedido atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
            })
    public void atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        pedidoService.atualizarPedido(id, pedido);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um pedido pelo ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Pedido deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
            })
    public void deletarPedido(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
    }
}
