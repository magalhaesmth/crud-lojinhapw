package com.lojinha.crud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.lojinha.crud.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    @Query(value = "Select a from Produto a where a.nome like %?1%") Page<Produto> findByNome(String nome, Pageable pageable);
    Page<Produto> findAll(Pageable pageable);
}
