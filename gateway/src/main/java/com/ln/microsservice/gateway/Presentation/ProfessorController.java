package com.ln.microsservice.gateway.Presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.ln.microsservice.gateway.Business.APiResponses.ApiResponsePattern;
import com.ln.microsservice.gateway.Business.Documentation.CriarTarefaApiDoc;
import com.ln.microsservice.gateway.Business.Documentation.NovoProfessorAPiDoc;
import com.ln.microsservice.gateway.Business.Documentation.ObterTarefasLancadasApiDoc;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/professor")
@Tag(name = "Professor", description = "Endpoints para o contexto do professor")
public class ProfessorController {

    @Autowired
    private WebClient webClient;

    @CriarTarefaApiDoc
    @PostMapping("/criar-tarefa")
    public ResponseEntity<?> criarTarefa(@RequestBody JsonNode entity) {
        ResponseEntity<?> response = webClient.post()
                .uri("/professor/criar-tarefa")
                .bodyValue(entity)
                .retrieve()
                .bodyToMono(ResponseEntity.class)
                .block();
        return response;
    }

    @NovoProfessorAPiDoc
    @PostMapping("/novo-professor")
    public ResponseEntity<?> novoProfessor(@RequestBody JsonNode entity) {
        ResponseEntity<?> response = webClient.post().uri("/professor/novo-professor").bodyValue(entity).retrieve()
                .bodyToMono(ResponseEntity.class).block();
        return response;
    }

    @ObterTarefasLancadasApiDoc
    @GetMapping("/tarefas/obter-todas")
    public ResponseEntity<?> obterTodasTarefas(@RequestParam UUID professorId) {
        JsonNode response = webClient.get().uri("/professor/tarefas/obter-todas?professorId=" + professorId).retrieve().bodyToMono(JsonNode.class)
                .block();
        ApiResponsePattern apiResponse = new ApiResponsePattern("Tarefas obtidas com sucesso", response);
        return ResponseEntity.ok(apiResponse);
    }
}
