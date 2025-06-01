package br.com.l2code.aulas.repository;

import br.com.l2code.aulas.model.Professor;
import br.com.l2code.aulas.model.dto.ProfessorCargaHorariaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query(value = """
        SELECT new br.com.l2code.aulas.model.dto.ProfessorCargaHorariaDTO(
            p.name,
            SUM(TIMESTAMPDIFF(MINUTE, cs.startTime, cs.endTime)) / 60.0
        )
        FROM Professor p
        JOIN Class c ON c.professor = p
        JOIN ClassSchedule cs ON cs.clazz = c
        GROUP BY p.name
        ORDER BY SUM(TIMESTAMPDIFF(MINUTE, cs.startTime, cs.endTime)) DESC
        """)
    List<ProfessorCargaHorariaDTO> obterCargaHorariaPorProfessor();
}

