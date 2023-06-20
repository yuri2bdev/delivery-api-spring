package br.com.delivery.deliveryapi.controller;

import br.com.delivery.deliveryapi.model.Entrega;
import br.com.delivery.deliveryapi.service.EntregaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entregas")
@Tag(name = "Entregas", description = "API para gerenciamento de entregas")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @GetMapping
    @Operation(summary = "Listar todas as entregas")
    public List<Entrega> listarEntregas() {
        return entregaService.listarEntregas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter uma entrega pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Entrega encontrada",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Entrega.class))),
                    @ApiResponse(responseCode = "404", description = "Entrega não encontrada")
            })
    public Entrega obterEntregaPorId(@PathVariable Long id) {
        return entregaService.obterEntregaPorId(id);
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova entrega",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Entrega cadastrada com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Entrega.class)))
            })
    public Entrega cadastrarEntrega(@RequestBody Entrega entrega) {
        return entregaService.cadastrarEntrega(entrega);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma entrega pelo ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Entrega atualizada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Entrega não encontrada")
            })
    public void atualizarEntrega(@PathVariable Long id, @RequestBody Entrega entrega) {
        entregaService.atualizarEntrega(id, entrega);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma entrega pelo ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Entrega deletada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Entrega não encontrada")
            })
    public void deletarEntrega(@PathVariable Long id) {
        entregaService.deletarEntrega(id);
    }
}
