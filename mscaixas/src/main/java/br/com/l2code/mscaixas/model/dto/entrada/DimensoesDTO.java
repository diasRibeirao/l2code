package br.com.l2code.mscaixas.model.dto.entrada;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DimensoesDTO(
        @NotNull(message = "A altura é obrigatória")
        @DecimalMin(value = "0.01", inclusive = true, message = "A altura deve ser maior que zero")
        BigDecimal altura,

        @NotNull(message = "A largura é obrigatória")
        @DecimalMin(value = "0.01", inclusive = true, message = "A largura deve ser maior que zero")
        BigDecimal largura,

        @NotNull(message = "O comprimento é obrigatório")
        @DecimalMin(value = "0.01", inclusive = true, message = "O comprimento deve ser maior que zero")
        BigDecimal comprimento
) {
}
