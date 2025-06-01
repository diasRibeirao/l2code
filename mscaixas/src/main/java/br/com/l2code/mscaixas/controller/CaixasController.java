package br.com.l2code.mscaixas.controller;

import br.com.l2code.mscaixas.model.Caixa;
import br.com.l2code.mscaixas.model.converter.CaixaConverter;
import br.com.l2code.mscaixas.model.dto.CaixaDTO;
import br.com.l2code.mscaixas.service.CaixasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@Tag(name = "Caixas")
@RequestMapping(path = "/caixas", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CaixasController {

    private final CaixasService service;

    private final CaixaConverter converter;

    @GetMapping
    @Operation(summary = "Listar todas as caixas cadastradas")
    public ResponseEntity<List<Caixa>> listar() {
        log.info("Request para listar todas as caixas cadastradas.");
        List<Caixa> list = service.listar();

        if (list.isEmpty()) {
            log.info("Nenhuma caixa encontrada.");
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(list);
    }


    @Operation(summary = "Buscar uma caixa pelo seu id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Caixa> buscarPeloId(@PathVariable Long id) {
        log.info("Request para buscar a caixa de id: {}", id);
        var caixa = service.buscarPeloId(id);
        return ResponseEntity.ok().body(caixa);
    }

    @Operation(summary = "Cadastrar uma caixa")
    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody CaixaDTO cadastrarCaixa) {
        log.info("Request para cadastrar uma caixa: {}", cadastrarCaixa.toString());
        var caixa = converter.parseDTO(cadastrarCaixa);
        caixa = service.cadastrar(caixa);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(caixa.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar os dados cadastrais de uma caixa")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> atualizar(@Valid @PathVariable Long id, @Valid @RequestBody CaixaDTO atualizarCaixa) {
        log.info("Request para atualizar os dados cadastrais de uma caixa de id : {} - {}", atualizarCaixa.toString(), id);
        var caixaBD = service.buscarPeloId(id);
        var caixa = converter.parseDTO(atualizarCaixa);
        caixa.setId(caixaBD.getId());
        service.atualizar(caixa);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remover uma caixa")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        log.info("Request para remover a caixa de id : {}", id);
        service.remover(id);
        return ResponseEntity.noContent().build();
    }


}