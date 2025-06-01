<h1 align="center">
Avaliação Técnica Java L2 Code - Exercício 2 - Horários de aula
</h1>

## Considerando o modelo ER abaixo:

## O professor Girafales se tornou o novo diretor da escola do chavito. E ele precisa saber algumas coisas:
- A quantidade de horas que cada professor tem comprometido em aulas - Então faça uma consulta SQL que traga essa informação.
``` bash
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
```

- Lista de salas com horários livres e ocupados - Pode usar SQL e a linguagem de programação que achar melhor.
``` bash
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

```

## Requisitos para executar o projeto
- Git instalado - [**Download**](https://git-scm.com/downloads).
- JDK 21 instalado - [**Download**](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html).
- Docker Desktop for Windows - [**Download**](https://docs.docker.com/desktop/setup/install/windows-install/).
  ### Opção executar Linux (Ubuntu)
- Docker for Ubuntu - [**Download**](https://docs.docker.com/engine/install/ubuntu/).

  
## Iniciando
``` bash
  # Clonar o projeto:
  $ git clone https://github.com/diasRibeirao/l2code.git

  # Entrar no diretório do projeto:
  $ cd l2code
  $ cd aulas
```

## Executando o projeto Spring Boot com Maven
```bash
  # Instalar as dependências:
  $ mvn clean install 

  # Rodar a aplicação:
  $ mvn spring-boot:run

  # Rodar a aplicação com o CMD:
  $ Executar o seguinte comando: java -jar target/aulas-0.0.1-SNAPSHOT.jar
```

## Executando o projeto com Docker
```bash
  # Criar imagem docker:
  $ docker build -t aulas .

  # Executa um contêiner baseado na imagem mscaixas:
  $ docker run -p 8082:8082 --name aulas-container aulas
```


## Banco de Dados

#### [**http://localhost:8082/h2-console/login.jsp**](http://localhost:8082/h2-console/login.jsp)
```bash
 spring.datasource.driverClassName=org.h2.Driver
 spring.datasource.url=jdbc:h2:mem:aulasdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
 spring.datasource.username=sa
 spring.datasource.password=
```

## Endpoints

#### [**http://localhost:8082/swagger-ui/index.html**](http://localhost:8082/swagger-ui/index.html)

![image](https://github.com/user-attachments/assets/6360b285-52c5-4d75-bdc8-41970abe3135)


### Caixas
```bash
  # A quantidade de horas que cada professor tem comprometido em aulas:
  # Método: GET
  http://localhost:8082/aulas/horarios-salas

  # Lista de salas com horários livres e ocupados
  # Método: GET
  http://localhost:8082/aulas/carga-horaria
  
```


<br /><br />
Emerson Dias de Oliveira<br />
https://github.com/diasRibeirao
