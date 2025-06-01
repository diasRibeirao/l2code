package br.com.l2code.aulas.model.dto;

import java.util.List;
import java.util.Map;

public record RoomScheduleDTO(
        Long idSala,
        String sala,
        Map<String, List<TimeIntervalDTO>> ocupadas,
        Map<String, List<TimeIntervalDTO>> livres
) {
}
