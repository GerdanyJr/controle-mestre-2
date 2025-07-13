package com.github.gerdanyjr.controle_mestre_backend.controller;

import com.github.gerdanyjr.controle_mestre_backend.dto.in.ClienteRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.ClienteResponse;
import com.github.gerdanyjr.controle_mestre_backend.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
@Tag(name = "Clientes", description = "Operações relacionadas a clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @Operation(description = "Cadastra um cliente", responses = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "CPF já cadastrado")
    })
    public ResponseEntity<ClienteResponse> create(@RequestBody @Valid ClienteRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteService.create(request));
    }

    @GetMapping
    @Operation(description = "Lista todos os clientes", responses = {
            @ApiResponse(responseCode = "200", description = "Clientes retornados com sucesso")
    })
    public ResponseEntity<List<ClienteResponse>> listar() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deleta um cliente por id", responses = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
