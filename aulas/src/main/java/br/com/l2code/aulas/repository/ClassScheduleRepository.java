package br.com.l2code.aulas.repository;

import br.com.l2code.aulas.model.ClassSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassScheduleRepository extends JpaRepository<ClassSchedule, Long> {

    List<ClassSchedule> findByRoomIdAndDayOfWeekOrderByStartTime(Long roomId, String dayOfWeek);

}
