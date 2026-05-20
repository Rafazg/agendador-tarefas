package com.zgdev.agendador_tarefas.controller;

import com.zgdev.agendador_tarefas.business.TarefasService;
import com.zgdev.agendador_tarefas.business.dto.TarefasDTO;
import com.zgdev.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping("")
    public ResponseEntity<TarefasDTO> gravarTarefas(@RequestBody TarefasDTO dto,
                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefas(token, dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {

        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefaPorId(@PathVariable String id) {
        tarefasService.deletarTarefaPorID(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> alterarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                               @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefasService.alterarStatus(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> alterarTarefas(@RequestBody TarefasDTO tarefasDTO,
                                                     @RequestParam("id") String id){
        return ResponseEntity.ok(tarefasService.updateTarefas(tarefasDTO, id));
    }
}
