package com.lojinha.crud.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import com.lojinha.crud.model.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String nome;
    private String cpf;
    private String email;
    private String observacao;
    private Date dataCasdatro;


    public UsuarioDTO converter(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        BeanUtils.copyProperties(usuario,usuarioDTO);
        return usuarioDTO;
    }

    public Page<UsuarioDTO> converterListaUsuarioDTO(Page<Usuario> pageUsuario){
        Page<UsuarioDTO> listaDTO = pageUsuario.map(this::converter);
        return listaDTO;
    }
}
