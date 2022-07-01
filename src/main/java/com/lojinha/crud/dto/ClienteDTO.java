package com.lojinha.crud.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import com.lojinha.crud.model.Cliente;
import lombok.Data;

@Data
public class ClienteDTO {
    private String nome;
    private String email;
    private String cpf;
    private String observacao;
    private Date dataCadastro;

    public ClienteDTO converter(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        BeanUtils.copyProperties(cliente,clienteDTO);
        return clienteDTO;
    }

    public Page<ClienteDTO> converterListaClienteDTO(Page<Cliente> pageCliente){
        Page<ClienteDTO> listaDTO = pageCliente.map(this::converter);
        return listaDTO;
    }

}
