package com.github.gerdanyjr.controle_mestre_backend.controller;

import com.github.gerdanyjr.controle_mestre_backend.dto.in.ProdutoRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.ProdutoResponse;
import com.github.gerdanyjr.controle_mestre_backend.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    @Operation(description = "Cadastrar um produto", responses = {
            @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada!")
    })
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody @Valid ProdutoRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoService.create(request));
    }

    @GetMapping
    @Operation(description = "Produtos retornados com sucesso", responses = {
            @ApiResponse(responseCode = "200", description = "Produtos retornados com sucesso")
    })
    public ResponseEntity<List<ProdutoResponse>> listarProdutos() {
        return ResponseEntity.ok(produtoService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(
            @PathVariable @Parameter(description = "Id do produto a ser deletado") Long id
    ) {
        produtoService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
