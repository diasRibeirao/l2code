package br.com.l2code.mscaixas.controller;

import br.com.l2code.mscaixas.model.dto.entrada.RequisicaoEmpacotamentoDTO;
import br.com.l2code.mscaixas.model.dto.saida.RespostaEmpacotamentoDTO;
import br.com.l2code.mscaixas.service.EmpacotadorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "Empacotamento")
@RequestMapping(path = "/empacotamento", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EmpacotamentoController {

    private final EmpacotadorService empacotadorService;

    @PostMapping
    public ResponseEntity<RespostaEmpacotamentoDTO> empacotar(@RequestBody @Valid RequisicaoEmpacotamentoDTO requisicao) {
        RespostaEmpacotamentoDTO resposta = empacotadorService.empacotarPedidos(requisicao);
        return ResponseEntity.ok(resposta);
    }
}
