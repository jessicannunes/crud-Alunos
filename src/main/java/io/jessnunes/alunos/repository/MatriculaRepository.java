package io.jessnunes.alunos.repository;

import io.jessnunes.alunos.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
}
