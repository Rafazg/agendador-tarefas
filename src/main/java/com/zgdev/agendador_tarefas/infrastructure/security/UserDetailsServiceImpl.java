package com.zgdev.agendador_tarefas.infrastructure.security;

import com.zgdev.agendador_tarefas.business.dto.UsuarioDTO;
import com.zgdev.agendador_tarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl{

    @Autowired
    private UsuarioClient usuarioClient;

    public UserDetails carregaDadosUsuario(String email, String token){
        UsuarioDTO usuarioDTO = usuarioClient.buscarUsuarioPorEmail(email, token);
        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
