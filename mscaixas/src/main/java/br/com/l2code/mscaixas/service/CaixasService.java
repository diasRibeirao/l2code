package br.com.l2code.mscaixas.service;

import br.com.l2code.mscaixas.model.Caixa;
import br.com.l2code.mscaixas.repository.CaixasRepository;
import br.com.l2code.mscaixas.service.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CaixasService {

    private CaixasRepository repository;

    public List<Caixa> listar() {
        return repository.findAll();
    }

    public Caixa buscarPeloId(Long id) {
        Optional<Caixa> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Caixa não encontrada! Id: " + id));
    }

    @Transactional
    public Caixa cadastrar(Caixa caixa) {
        return repository.save(caixa);
    }

    @Transactional
    public Caixa atualizar(Caixa caixa) {
        buscarPeloId(caixa.getId());
        return repository.save(caixa);
    }

    public void remover(Long id) {
        buscarPeloId(id);
        repository.deleteById(id);
    }
}
