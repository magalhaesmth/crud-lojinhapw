package com.lojinha.crud.service;

import com.lojinha.crud.exceptions.BadResourceException;
import com.lojinha.crud.exceptions.ResourceAlreadyExistsException;
import com.lojinha.crud.exceptions.ResourceNotFoundException;
import com.lojinha.crud.model.Cliente;
import com.lojinha.crud.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    private boolean existsById(long id) {
        return clienteRepository.existsById(id);
    }

    public Cliente findById(Long id) throws ResourceNotFoundException {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente==null) {
            throw new ResourceNotFoundException("Cliente não encontrado com o id:" + id);
        }
        else return cliente;
    }

    public Page<Cliente> findAll(Pageable pageable){
        return clienteRepository.findAll(pageable);
    }

    public Page<Cliente> findAllByNome(String nome, Pageable pageable){
        Page<Cliente> Clientes = clienteRepository.findByNome(nome, pageable);
        return Clientes;
    }

    public Cliente save(Cliente cliente) throws BadResourceException, ResourceAlreadyExistsException{
        if (!StringUtils.isEmpty(cliente.getNome())) {
            cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
            if (Long.toString(cliente.getId()) != null && existsById(cliente.getId())) {
                throw new ResourceAlreadyExistsException("Cliente com id: " + cliente.getId() + "já existe");

            }
            return clienteRepository.save(cliente);
        }
        else {
            BadResourceException exc =new BadResourceException("Erro ao salvar o cliente");
            exc.addErrorMessage("Cliente está vazio ou é nulo");
            throw exc;
        }
    }

    public void update(Cliente cliente)
            throws BadResourceException, ResourceNotFoundException{
        if(!StringUtils.isEmpty(cliente.getNome())) {
            if (!existsById(cliente.getId())) {
            }
            clienteRepository.save(cliente);
        }
        else {
            BadResourceException exc = new BadResourceException("Falha ao salvar Cliente");
            exc.addErrorMessage("Cliente está nulo ou em branco");
            throw exc;
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException{
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado com id: "+ id);
        }
        else {
            clienteRepository.deleteById(id);
        }
    }

    public Long count() {
        return clienteRepository.count();
    }
}

