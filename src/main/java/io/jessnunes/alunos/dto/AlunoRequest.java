package io.jessnunes.alunos.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public record AlunoRequest(String nome, String telefone, LocalDate dataNascimento, List<MatriculaDTO> matriculas) {
}
