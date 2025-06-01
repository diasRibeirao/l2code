-- Inserir departamentos
INSERT INTO DEPARTMENT (name) VALUES ('Educação'), ('Matemática'), ('Línguas');

-- Inserir títulos
INSERT INTO TITLE (name) VALUES ('Professor'), ('Diretor');

-- Inserir professores
INSERT INTO PROFESSOR (name, department_id, title_id) VALUES
  ('Girafales', 1, 2),
  ('Xavier', 2, 1),
  ('Helena', 1, 1),
  ('Raimundo', 1, 1),
  ('Luana', 3, 1),
  ('Newton', 2, 1),
  ('Marcelo', 2, 1),
  ('Clarice', 3, 1),
  ('Maurício', 1, 1),
  ('Márcia', 2, 1),
  ('Miguel', 2, 1),
  ('José', 3, 1),
  ('Emerson', 1, 1);

-- Inserir prédios
INSERT INTO BUILDING (name) VALUES ('Bloco A'), ('Bloco B');

-- Inserir salas
INSERT INTO ROOM (name, building_id) VALUES 
  ('Sala 101', 1),
  ('Sala 102', 1),
  ('Sala 201', 2),
  ('Sala 202', 2),
  ('Sala 301', 1);

-- Inserir matérias
INSERT INTO SUBJECT (code, name) VALUES 
  ('EDU101', 'Didática'),
  ('MAT201', 'Álgebra'),
  ('EDU202', 'Psicologia da Educação'),
  ('LIN301', 'Gramática'),
  ('MAT301', 'Cálculo I'),
  ('MAT302', 'Cálculo II'),
  ('MAT303', 'Cálculo III'),
  ('LIN101', 'Leitura e Interpretação'),
  ('MAT401', 'Geometria'),
  ('EDU303', 'Metodologia de Ensino'),
  ('LIN401', 'Redação'),
  ('EDU404', 'Avaliação Educacional'),
  ('EDU406', 'Avaliação Comportamental');

-- Inserir pré-requisitos
INSERT INTO SUBJECT_PREREQUISITE (subject_id, prerequisiteid) VALUES 
  (3, 1), -- Psicologia requer Didática
  (6, 5), -- Cálculo II requer Cálculo I
  (11, 1); -- Avaliação Educacional requer Didática

-- Inserir turmas
INSERT INTO CLASS (subject_id, professor_id, class_year, semester, code) VALUES 
  (1, 1, 2025, 1, 'T1'),   
  (2, 2, 2025, 1, 'T2'),   
  (3, 3, 2025, 1, 'T3'),   
  (4, 4, 2025, 1, 'T4'),   
  (5, 5, 2025, 1, 'T5'),   
  (6, 6, 2025, 1, 'T6'),   
  (7, 7, 2025, 1, 'T7'),   
  (8, 8, 2025, 1, 'T8'),   
  (9, 9, 2025, 1, 'T9'),   
  (10, 10, 2025, 1, 'T10'),
  (11, 11, 2025, 1, 'T11'),
  (12, 12, 2025, 2, 'T12');


-- Inserir horários das turmas (segunda a sexta, horários variados)
INSERT INTO CLASS_SCHEDULE (class_id, room_id, day_of_week, start_time, end_time) VALUES 
  (1, 1, 'Monday',    '08:00:00', '10:00:00'),
  (2, 2, 'Monday',    '10:15:00', '12:00:00'),
  (3, 3, 'Monday',    '13:00:00', '15:00:00'),  
  (4, 4, 'Tuesday',   '08:00:00', '09:45:00'),
  (5, 5, 'Tuesday',   '10:00:00', '12:00:00'),
  (6, 1, 'Tuesday',   '13:00:00', '15:00:00'),
  (7, 2, 'Wednesday', '08:00:00', '10:00:00'),
  (8, 3, 'Wednesday', '10:15:00', '12:00:00'),
  (9, 4, 'Wednesday', '13:00:00', '15:00:00'),
  (10, 5, 'Thursday', '08:00:00', '09:30:00'),
  (11, 1, 'Thursday', '10:00:00', '12:00:00'),
  (12, 2, 'Thursday', '13:00:00', '15:00:00'),
  (1, 3, 'Friday',    '08:00:00', '10:00:00'),
  (2, 4, 'Friday',    '10:15:00', '12:00:00'),
  (3, 5, 'Friday',    '13:00:00', '15:00:00');




