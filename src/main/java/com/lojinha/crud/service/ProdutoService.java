package com.lojinha.crud.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import com.lojinha.crud.model.Produto;
import com.lojinha.crud.exceptions.BadResourceException;
import com.lojinha.crud.exceptions.ResourceAlreadyExistsException;
import com.lojinha.crud.exceptions.ResourceNotFoundException;
import com.lojinha.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    private boolean existsById(long id) {
        return produtoRepository.existsById(id);
    }

    public Produto findById(Long id) throws ResourceNotFoundException {
        Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto==null) {
            throw new ResourceNotFoundException("Produto não encontrado com o id:" + id);
        }
        else return produto;
    }

    public Page<Produto> findAll(Pageable pageable){
        return produtoRepository.findAll(pageable);
    }

    public Page<Produto> findAllByNome(String nome, Pageable pageable){
        Page<Produto> produtos = produtoRepository.findByNome(nome, pageable);
        return produtos;
    }

    public Produto save(Produto produto) throws BadResourceException, ResourceAlreadyExistsException{
        if (!StringUtils.isEmpty(produto.getNome())) {
            if (Long.toString(produto.getId()) != null && existsById(produto.getId())) {
                throw new ResourceAlreadyExistsException("Produto com id: " + produto.getId() +    "já existe");

            }
            return produtoRepository.save(produto);
        }
        else {
            BadResourceException exc =new BadResourceException("Erro ao salvar o Produto");
            exc.addErrorMessage("Produto está vazio ou é nulo");
            throw exc;
        }
    }

    public void update(Produto produto)
            throws BadResourceException, ResourceNotFoundException{
        if(!StringUtils.isEmpty(produto.getNome())) {
            if (!existsById(produto.getId())) {
            }
            produtoRepository.save(produto);
        }
        else {
            BadResourceException exc = new BadResourceException("Falha ao salvar Produto");
            exc.addErrorMessage("Produto está nulo ou em branco");
            throw exc;
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException{
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado com id: "+ id);
        }
        else {
            produtoRepository.deleteById(id);
        }
    }

    public Long count() {
        return produtoRepository.count();
    }
}
