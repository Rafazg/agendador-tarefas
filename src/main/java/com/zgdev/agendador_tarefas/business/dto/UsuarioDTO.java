package com.zgdev.agendador_tarefas.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private String email;
    private String senha;
}