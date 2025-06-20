package io.jessnunes.alunos.mapper;

import io.jessnunes.alunos.dto.AlunoRequest;
import io.jessnunes.alunos.dto.AlunoResponse;
import io.jessnunes.alunos.dto.MatriculaDTO;
import io.jessnunes.alunos.model.Aluno;
import io.jessnunes.alunos.model.Matricula;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlunoMapper {

    public Aluno toEntity(AlunoRequest request){
        Aluno aluno = new Aluno();
        aluno.setNome(request.nome());
        aluno.setDataNascimento(request.dataNascimento());
        aluno.setTelefone(request.telefone());

        List<Matricula> matriculas = request.matriculas().stream().map(m->{
                Matricula matricula = new Matricula();
                matricula.setCodMatricula(m.codigoMatricula());
                matricula.setDataInicio(m.dataInicio());
                matricula.setNomeCurso(m.nomeCurso());

                return matricula;
        }).toList();
        aluno.setMatriculas(matriculas);
        return aluno;
    }

    public AlunoResponse toResponse(Aluno aluno){
        List<MatriculaDTO> matriculasDTO = aluno.getMatriculas().stream().map(m->
            new MatriculaDTO(m.getCodMatricula(),
                    m.getNomeCurso(),
                    m.getDataInicio())).toList();
        return new AlunoResponse(aluno.getId(),
                aluno.getNome(),
                aluno.getTelefone(),
                aluno.getDataNascimento(),
                matriculasDTO);
    }
}
