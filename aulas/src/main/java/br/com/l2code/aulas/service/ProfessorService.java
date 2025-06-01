package br.com.l2code.aulas.service;

import br.com.l2code.aulas.model.dto.ProfessorCargaHorariaDTO;
import br.com.l2code.aulas.repository.ProfessorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repository;

    public List<ProfessorCargaHorariaDTO> obterCargaHorariaPorProfessor() {
        return repository.obterCargaHorariaPorProfessor();
    }
}
