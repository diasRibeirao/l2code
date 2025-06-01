package br.com.l2code.mscaixas.model.dto.saida;

import java.util.List;

public record RespostaEmpacotamentoDTO(
        List<PedidoEmpacotadoDTO> pedidos
) {}
