package com.github.gerdanyjr.controle_mestre_backend.dto.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Dto para cadastro de venda")
public record VendaRequest(
        @Schema(description = "Data da venda", example = "2024-05-05")
        @Past(message = "A data da venda não pode ser posterior a data atual!")
        LocalDate data,

        @NotEmpty(message = "Uma venda deve conter no mínimo um produto")
        @Schema(description = "Produtos vendidos", example = "[{\"id\": 1, \"quantidade\": 2}, {\"id\": 2, \"quantidade\": 1}]")
        List<ProdutoVendaRequest> produtos
) {
}
