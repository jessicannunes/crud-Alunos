package io.jessnunes.alunos.controller;

import io.jessnunes.alunos.dto.AlunoRequest;
import io.jessnunes.alunos.dto.AlunoResponse;
import io.jessnunes.alunos.dto.MatriculaDTO;
import io.jessnunes.alunos.model.Aluno;
import io.jessnunes.alunos.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;
    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> criar(@RequestBody AlunoRequest alunoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.salvar(alunoRequest));
    }

    @GetMapping
    public List<AlunoResponse> listarTodos(){
        return alunoService.listarTodos();
    }

    @GetMapping("/{id}/matriculas")
    public List<MatriculaDTO> matriculaDTOS(@PathVariable Long id){
        return alunoService.listarMatriculas(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> atulizar(@PathVariable Long id,
                                                  @RequestBody AlunoRequest alunoRequest){
        return ResponseEntity.ok(alunoService.atualizar(id, alunoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(Long id){
        alunoService.remover(id);
        return ResponseEntity.noContent().build();
    }

}
