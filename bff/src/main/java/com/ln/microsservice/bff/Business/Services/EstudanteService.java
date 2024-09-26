package com.ln.microsservice.bff.Business.Services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ln.microsservice.bff.Business.Config.AmqpConfig;
import com.ln.microsservice.bff.DTO.EstudanteRegisterReqDTO;

@Service
public class EstudanteService {

    @Autowired
    private AmqpConfig amqpConfig;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = JsonMapper.builder().findAndAddModules().build();

    public void criarEstudante(EstudanteRegisterReqDTO estudante) throws JsonProcessingException {
        String estudanteDtoJson = objectMapper.writeValueAsString(estudante);
        rabbitTemplate.convertAndSend(amqpConfig.estudantesQueue().getName(), estudanteDtoJson);
    }
}
