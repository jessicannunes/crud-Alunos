package io.jessnunes.alunos.service;

import io.jessnunes.alunos.dto.AlunoRequest;
import io.jessnunes.alunos.dto.AlunoResponse;
import io.jessnunes.alunos.dto.MatriculaDTO;
import io.jessnunes.alunos.mapper.AlunoMapper;
import io.jessnunes.alunos.model.Aluno;
import io.jessnunes.alunos.model.Matricula;
import io.jessnunes.alunos.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoMapper alunoMapper;
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoMapper alunoMapper, AlunoRepository alunoRepository){
        this.alunoMapper = alunoMapper;
        this.alunoRepository = alunoRepository;
    }

    public AlunoResponse salvar(AlunoRequest alunoRequest){
        Aluno aluno = alunoMapper.toEntity(alunoRequest);
        alunoRepository.save(aluno);
        return alunoMapper.toResponse(aluno);
    }

    public List<AlunoResponse> listarTodos(){
        return alunoRepository.findAll().stream().map(alunoMapper::toResponse).toList();
    }

    public List<MatriculaDTO> listarMatriculas(Long id){
        Aluno aluno = alunoRepository.findById(id).
                orElseThrow(()-> new EntityNotFoundException("Aluno não encontrado"));
        return aluno.getMatriculas().stream().map(m -> new MatriculaDTO(m.getCodMatricula(),
                m.getNomeCurso(),
                m.getDataInicio())).toList();
    }

    public void remover(Long id){
        if(!alunoRepository.existsById(id)){
            throw new EntityNotFoundException("Id do Aluno não encontrado");
        }else{
            alunoRepository.deleteById(id);
        }
    }

    public AlunoResponse atualizar(Long id, AlunoRequest alunoRequest){
        Aluno a = alunoRepository.findById(id).
                orElseThrow(()-> new EntityNotFoundException("Aluno não encontrado"));
        a.setNome(alunoRequest.nome());
        a.setTelefone(alunoRequest.telefone());
        a.setDataNascimento(alunoRequest.dataNascimento());

        for(MatriculaDTO m: alunoRequest.matriculas()){
            Matricula matricula = new Matricula();
            matricula.setCodMatricula(m.codigoMatricula());
            matricula.setNomeCurso(m.nomeCurso());
            matricula.setNomeCurso(m.nomeCurso());
            a.getMatriculas().add(matricula);
        }
        return alunoMapper.toResponse(alunoRepository.save(a));
    }

}
