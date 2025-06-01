package br.com.l2code.mscaixas.model.dto.entrada;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PedidoDTO(
        @NotNull
        Integer pedido_id,

        @NotNull(message = "A lista de produtos é obrigatória")
        @Size(min = 1, message = "A lista de produtos deve conter ao menos um produto")
        List<@Valid ProdutoDTO> produtos
) {
}
