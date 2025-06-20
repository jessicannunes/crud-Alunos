package io.jessnunes.alunos.dto;

import java.time.LocalDate;

public record MatriculaDTO(String codigoMatricula, String nomeCurso, LocalDate dataInicio) {
}
