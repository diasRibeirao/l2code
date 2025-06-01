package br.com.l2code.aulas.service;

import br.com.l2code.aulas.model.ClassSchedule;
import br.com.l2code.aulas.model.Room;
import br.com.l2code.aulas.model.dto.RoomScheduleDTO;
import br.com.l2code.aulas.model.dto.TimeIntervalDTO;
import br.com.l2code.aulas.model.enums.DaysOfWeek;
import br.com.l2code.aulas.repository.ClassScheduleRepository;
import br.com.l2code.aulas.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor

public class RoomScheduleService {

    private final RoomRepository roomRepository;
    private final ClassScheduleRepository scheduleRepository;

    private static final LocalTime INICIO = LocalTime.of(8, 0);
    private static final LocalTime FIM = LocalTime.of(18, 0);

    public List<RoomScheduleDTO> listarHorarios() {
        List<Room> salas = roomRepository.findAll();
        List<RoomScheduleDTO> resultado = new ArrayList<>();

        for (Room sala : salas) {
            Map<String, List<TimeIntervalDTO>> ocupadosPorDia = new HashMap<>();
            Map<String, List<TimeIntervalDTO>> livresPorDia = new HashMap<>();

            for (DaysOfWeek dia : DaysOfWeek.values()) {
                String diaStr = dia.name();

                List<ClassSchedule> agendamentos = scheduleRepository
                        .findByRoomIdAndDayOfWeekOrderByStartTime(sala.getId(), diaStr);

                List<TimeIntervalDTO> ocupados = agendamentos.stream()
                        .map(s -> new TimeIntervalDTO(s.getStartTime().toLocalTime(), s.getEndTime().toLocalTime()))
                        .toList();

                ocupadosPorDia.put(diaStr, ocupados);

                List<TimeIntervalDTO> livres = calcularLivres(ocupados, INICIO, FIM);
                livresPorDia.put(diaStr, livres);
            }

            resultado.add(new RoomScheduleDTO(
                    sala.getId(),
                    sala.getName(),
                    ocupadosPorDia,
                    livresPorDia
            ));
        }

        return resultado;
    }

    private List<TimeIntervalDTO> calcularLivres(List<TimeIntervalDTO> ocupados, LocalTime inicio, LocalTime fim) {
        List<TimeIntervalDTO> livres = new ArrayList<>();
        if (ocupados.isEmpty()) {
            livres.add(new TimeIntervalDTO(inicio, fim));
            return livres;
        }

        ocupados = ocupados.stream()
                .sorted(Comparator.comparing(TimeIntervalDTO::inicio))
                .toList();

        if (inicio.isBefore(ocupados.getFirst().inicio())) {
            livres.add(new TimeIntervalDTO(inicio, ocupados.getFirst().inicio()));
        }

        for (int i = 0; i < ocupados.size() - 1; i++) {
            LocalTime fimAtual = ocupados.get(i).fim();
            LocalTime inicioProximo = ocupados.get(i + 1).inicio();
            if (fimAtual.isBefore(inicioProximo)) {
                livres.add(new TimeIntervalDTO(fimAtual, inicioProximo));
            }
        }

        if (ocupados.getLast().fim().isBefore(fim)) {
            livres.add(new TimeIntervalDTO(ocupados.getLast().fim(), fim));
        }

        return livres;
    }
}
