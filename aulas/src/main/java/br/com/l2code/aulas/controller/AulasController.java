package br.com.l2code.aulas.controller;

import br.com.l2code.aulas.model.dto.ProfessorCargaHorariaDTO;
import br.com.l2code.aulas.model.dto.RoomScheduleDTO;
import br.com.l2code.aulas.service.ProfessorService;
import br.com.l2code.aulas.service.RoomScheduleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "Aulas")
@RequestMapping(path = "/aulas", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AulasController {

    private final ProfessorService professorService;

    private final RoomScheduleService roomScheduleService;

    @GetMapping("/carga-horaria")
    public List<ProfessorCargaHorariaDTO> getCargaHorariaProfessores() {
        return professorService.obterCargaHorariaPorProfessor();
    }

    @GetMapping("/horarios-salas")
    public List<RoomScheduleDTO> getHorariosSala() {
        return roomScheduleService.listarHorarios();
    }

}
