package br.com.l2code.aulas.model.dto;

import java.time.LocalTime;

public record TimeIntervalDTO(
        LocalTime inicio,
        LocalTime fim
) {
}
