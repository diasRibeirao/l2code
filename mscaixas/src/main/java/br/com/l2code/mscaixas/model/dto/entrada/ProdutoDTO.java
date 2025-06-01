package br.com.l2code.mscaixas.model.dto.entrada;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(
        @NotNull(message = "O id do produto é obrigatória")
        String produto_id,

        @NotNull(message = "As dimensões do produto é obrigatória")
        @Valid DimensoesDTO dimensoes
) {}

