package com.zgdev.agendador_tarefas.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zgdev.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefasDTO {

    private String id;
    private String nomeTarefa;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private String emailUsuario;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDate dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;
}
