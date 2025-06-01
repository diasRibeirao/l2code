package br.com.l2code.aulas.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DaysOfWeek {

    Monday("Segunda-feira"),
    Tuesday("Terça-feira"),
    Wednesday("Quarta-feira"),
    Thursday("Quinta-feira"),
    Friday("Sexta-feira");

    private final String label;

}
