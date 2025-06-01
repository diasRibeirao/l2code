package br.com.l2code.aulas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "CLASS_SCHEDULE ")
@Getter
@Setter
@ToString
public class ClassSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Class clazz;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private String dayOfWeek;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
