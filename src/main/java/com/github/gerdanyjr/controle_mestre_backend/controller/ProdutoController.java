package com.github.gerdanyjr.controle_mestre_backend.controller;

import com.github.gerdanyjr.controle_mestre_backend.dto.in.ProdutoRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.ProdutoResponse;
import com.github.gerdanyjr.controle_mestre_backend.service.ProdutoService;
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
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody @Valid ProdutoRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoService.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarProdutos() {
        return ResponseEntity.ok(produtoService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
