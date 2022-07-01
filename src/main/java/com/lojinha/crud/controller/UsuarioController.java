package com.lojinha.crud.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.lojinha.crud.model.Usuario;
import com.lojinha.crud.exceptions.BadResourceException;
import com.lojinha.crud.exceptions.ResourceNotFoundException;
import com.lojinha.crud.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Usuario>> findAll(@RequestBody(required=false) String nome, Pageable pageable){

        if(StringUtils.isEmpty(nome)) {
            return ResponseEntity.ok(usuarioService.findAll(pageable));
        }
        else {
            return ResponseEntity.ok(usuarioService.findAllByNome(nome,pageable));
        }
    }
    @Operation(summary = "Busca ID", description = "Buscar Usuario por ID", tags = {"usuario"})
    @GetMapping(value = "/usuario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> findUsuarioById(@PathVariable long id){

        try {
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(),ex);
        }
    }

    @PutMapping(value = "/usuario/{id}")
    public ResponseEntity<Usuario>updateusuario(@Valid @RequestBody Usuario usuario, @PathVariable long id) throws ResourceNotFoundException{
        try {
            usuario.setId(id);
            usuarioService.update(usuario);
            return ResponseEntity.ok().build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(path="/usuario/{id}")
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable long id){
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
}
