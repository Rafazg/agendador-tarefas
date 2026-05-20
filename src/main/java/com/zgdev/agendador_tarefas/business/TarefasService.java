package com.zgdev.agendador_tarefas.business;

import com.zgdev.agendador_tarefas.business.dto.TarefasDTO;
import com.zgdev.agendador_tarefas.business.mapper.TarefasConverter;
import com.zgdev.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.zgdev.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.zgdev.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.zgdev.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefas(String token, TarefasDTO dto){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefasConverter.paraTarefasEntity(dto);

        return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
         return tarefasConverter.paraListaTarefasDTO
                 (tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscarTarefasPorEmail(String token){

        String email = jwtUtil.extrairEmailToken(token.substring(7));

        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);
        return tarefasConverter.paraListaTarefasDTO(listaTarefas);
    }
}
