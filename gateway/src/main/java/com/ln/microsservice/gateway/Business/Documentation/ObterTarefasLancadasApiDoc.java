package com.ln.microsservice.gateway.Business.Documentation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Operation;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Operation(
    summary = "Obtém todas as tarefas lançadas",
    description = "Obtém todas as tarefas lançadas no sistema pelo professor"
)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ObterTarefasLancadasApiDoc {

}
