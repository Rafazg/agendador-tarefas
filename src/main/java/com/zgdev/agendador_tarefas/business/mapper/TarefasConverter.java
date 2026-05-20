package com.zgdev.agendador_tarefas.business.mapper;

import com.zgdev.agendador_tarefas.business.dto.TarefasDTO;
import com.zgdev.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    TarefasEntity paraTarefasEntity(TarefasDTO dto);
    TarefasDTO paraTarefasDTO(TarefasEntity entity);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dtos);
    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> entitys);
}
