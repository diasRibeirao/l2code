package br.com.l2code.mscaixas.model.dto.entrada;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record RequisicaoEmpacotamentoDTO(
        @NotEmpty(message = "É necessário fornecer pelo menos um pedido")
        List<@Valid PedidoDTO> pedidos
) {}
