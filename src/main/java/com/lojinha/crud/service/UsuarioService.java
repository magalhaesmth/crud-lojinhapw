package com.lojinha.crud.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import com.lojinha.crud.model.Usuario;
import com.lojinha.crud.exceptions.BadResourceException;
import com.lojinha.crud.exceptions.ResourceAlreadyExistsException;
import com.lojinha.crud.exceptions.ResourceNotFoundException;
import com.lojinha.crud.repository.UsuarioRepository;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private boolean existsById(long id) {
        return usuarioRepository.existsById(id);
    }

    public Usuario findById(Long id) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario==null) {
            throw new ResourceNotFoundException("Usuario não encontrado com o id:" + id);
        }
        else return usuario;
    }

    public Page<Usuario> findAll(Pageable pageable){
        return usuarioRepository.findAll(pageable);
    }

    public Page<Usuario> findAllByNome(String nome, Pageable pageable){
        Page<Usuario> Usuarios = usuarioRepository.findByNome(nome, pageable);
        return Usuarios;
    }

    public Usuario save(Usuario usuario) throws BadResourceException, ResourceAlreadyExistsException{
        if (!StringUtils.isEmpty(usuario.getNome())) {
            if (Long.toString(usuario.getId()) != null && existsById(usuario.getId())) {
                throw new ResourceAlreadyExistsException("Usuario com id: " + usuario.getId() +    "já existe");

            }
            return usuarioRepository.save(usuario);
        }
        else {
            BadResourceException exc =new BadResourceException("Erro ao salvar o Usuario");
            exc.addErrorMessage("Usuario está vazio ou é nulo");
            throw exc;
        }
    }

    public void update(Usuario usuario)
            throws BadResourceException, ResourceNotFoundException{
        if(!StringUtils.isEmpty(usuario.getNome())) {
            if (!existsById(usuario.getId())) {
            }
            usuarioRepository.save(usuario);
        }
        else {
            BadResourceException exc = new BadResourceException("Falha ao salvar Usuario");
            exc.addErrorMessage("Usuario está nulo ou em branco");
            throw exc;
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException{
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Usuario não encontrado com id: "+ id);
        }
        else {
            usuarioRepository.deleteById(id);
        }
    }

    public Long count() {
        return usuarioRepository.count();
    }
}
