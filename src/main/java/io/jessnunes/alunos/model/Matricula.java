package io.jessnunes.alunos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Matricula extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codMatricula;
    private String nomeCurso;
    private LocalDate dataInicio;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
}
