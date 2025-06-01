package br.com.l2code.mscaixas.model.dto.saida;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CaixaEmpacotadaDTO(
        String caixa_id,
        List<String> produtos,
        String observacao
) {
}
