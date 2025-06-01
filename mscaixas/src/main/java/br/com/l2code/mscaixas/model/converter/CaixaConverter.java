package br.com.l2code.mscaixas.model.converter;

import br.com.l2code.mscaixas.model.Caixa;
import br.com.l2code.mscaixas.model.dto.CaixaDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CaixaConverter {

    public CaixaDTO parse(Caixa origin) {
        if (origin == null)
            return null;

        return new CaixaDTO(
                origin.getAltura(),
                origin.getLargura(),
                origin.getComprimento()
        );
    }

    public List<CaixaDTO> parse(List<Caixa> origin) {
        if (origin == null)
            return Collections.emptyList();

        return origin.stream().map(this::parse).collect(Collectors.toList());
    }

    public Caixa parseDTO(CaixaDTO origin) {
        if (origin == null)
            return null;

        Caixa caixa = new Caixa(origin.altura(), origin.largura(), origin.comprimento());

        return caixa;
    }

    public List<Caixa> parseDTO(List<CaixaDTO> origin) {
        if (origin == null)
            return Collections.emptyList();

        return origin.stream().map(this::parseDTO).collect(Collectors.toList());
    }
}
