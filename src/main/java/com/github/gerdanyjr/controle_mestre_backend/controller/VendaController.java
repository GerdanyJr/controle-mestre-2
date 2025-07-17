package com.github.gerdanyjr.controle_mestre_backend.controller;

import com.github.gerdanyjr.controle_mestre_backend.dto.in.VendaRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.VendaResponse;
import com.github.gerdanyjr.controle_mestre_backend.service.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vendas")
@Tag(name = "Vendas", description = "Operações relacionadas a venda")
public class VendaController {
    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    @Operation(description = "cadastra uma venda", responses = {
            @ApiResponse(responseCode = "201", description = "Venda cadastrada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Entidade não encontrada!")
    })
    public ResponseEntity<VendaResponse> cadastrarVenda(@RequestBody @Valid VendaRequest vendaRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vendaService.criar(vendaRequest));
    }

    @GetMapping("/{id}")
    @Operation(description = "Busca uma venda pelo id", responses = {
            @ApiResponse(responseCode = "200", description = "Venda encontrada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada!")
    })
    public ResponseEntity<VendaResponse> buscarVendaPorId(
            @PathVariable @Parameter(description = "Id da venda que será procurada", example = "1")
            Long id
    ) {
        return ResponseEntity.ok(vendaService.buscarPorId(id));
    }

    @GetMapping
    @Operation(description = "Busca por todas as vendas", responses = {
            @ApiResponse(responseCode = "200", description = "Vendas retornadas com sucesso")
    })
    public ResponseEntity<List<VendaResponse>> buscarTodas() {
        return ResponseEntity.ok(vendaService.buscarTodos());
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deleta uma venda", responses = {
            @ApiResponse(responseCode = "204", description = "Venda deletada com sucesso")
    })
    public ResponseEntity<Void> deletarVenda(
            @PathVariable @Parameter(description = "Id da venda que será deletada", example = "1")
            Long id
    ) {
        vendaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
