package com.github.gerdanyjr.controle_mestre_backend.controller;

import com.github.gerdanyjr.controle_mestre_backend.dto.in.FuncionarioRequest;
import com.github.gerdanyjr.controle_mestre_backend.dto.out.FuncionarioResponse;
import com.github.gerdanyjr.controle_mestre_backend.service.FuncionarioService;
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
@RequestMapping("api/v1/funcionarios")
@Tag(name = "Funcionário", description = "Operações relacionadas a produtos")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    @Operation(description = "Cadastra um funcionário", responses = {
            @ApiResponse(responseCode = "201", description = "funcionário cadastrado com sucesso"),
            @ApiResponse(responseCode = "409", description = "CPF já cadastrado")
    })
    public ResponseEntity<FuncionarioResponse> save(@RequestBody @Valid FuncionarioRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(funcionarioService.createFuncionario(request));
    }

    @GetMapping
    @Operation(description = "Busca todos os funcionários", responses = {
            @ApiResponse(responseCode = "200", description = "Funcionários retornados com sucesso")
    })
    public ResponseEntity<List<FuncionarioResponse>> findAll() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(funcionarioService.findAllFuncionarios());
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deleta um funcionário", responses = {
            @ApiResponse(responseCode = "204", description = "Funcionário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    public ResponseEntity<FuncionarioResponse> delete(
            @PathVariable @Parameter(description = "Id do funcionário a ser deletado") Long id
    ) {
        funcionarioService.deleteFuncionario(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
