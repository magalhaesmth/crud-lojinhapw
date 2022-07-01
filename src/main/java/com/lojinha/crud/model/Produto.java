package com.lojinha.crud.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax. persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints. NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "produto")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Produto implements Serializable {

    private static final long serialVersionUID = 4048798961366546485L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Schema(description = "Nome do Produto", example = "Carvao", required = true)
    private String nome;

    @Schema(description = "Preço do Produto", example = "15.00", required = true)
    private long preco;

    @Column(length = 4000)
    private String observacao;


}