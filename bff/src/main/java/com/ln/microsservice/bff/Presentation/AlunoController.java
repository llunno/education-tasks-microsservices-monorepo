package com.ln.microsservice.bff.Presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ln.microsservice.bff.Business.Services.EstudanteService;
import com.ln.microsservice.bff.Business.Services.TarefaService;
import com.ln.microsservice.bff.DTO.EstudanteDTO;
import com.ln.microsservice.bff.DTO.EstudanteRegisterReqDTO;
import com.ln.microsservice.bff.DTO.TarefaDTO;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private EstudanteService estudanteService;

    @GetMapping("/{userId}/tarefas")
    public ResponseEntity<?> getTodasTarefas(@PathVariable UUID userId) {
        List<TarefaDTO> tarefasPendentes = tarefaService.getAllTarefas(userId);
        return ResponseEntity.ok(tarefasPendentes);
    }

    @GetMapping("/{userId}/tarefas-vencidas")
    public ResponseEntity<?> getTarefasVencidas(@PathVariable UUID userId) {
        List<TarefaDTO> tarefasVencidas = tarefaService.getTarefasVencidas(userId);
        return ResponseEntity.ok(tarefasVencidas);
    }

    @PostMapping("/novo-estudante")
    public Boolean criarEstudante(@RequestBody EstudanteRegisterReqDTO estudante) throws JsonProcessingException {
        estudanteService.criarEstudante(estudante);
        return true;
    }

    @GetMapping("/obter-todos-estudantes")
    public ResponseEntity<?> obterTodosEstudantes() {
        List<EstudanteDTO> estudantes = estudanteService.getTodosEstudantes();
        return ResponseEntity.ok(estudantes);
    }

    @GetMapping("/{userId}/notificacoes")
    public ResponseEntity<?> obterNotificacoes(@PathVariable UUID userId) {
        return ResponseEntity.ok().body(estudanteService.obterNotificacoes(userId));
    }
}
