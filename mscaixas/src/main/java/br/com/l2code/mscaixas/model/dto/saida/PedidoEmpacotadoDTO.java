package br.com.l2code.mscaixas.model.dto.saida;

import java.util.List;

public record PedidoEmpacotadoDTO(
        Integer pedido_id,
        List<CaixaEmpacotadaDTO> caixas
) {}