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
@Table(name = "usuario")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Schema(description = "Nome do usuario", example = "jose", required = true)
    private String nome;

    @NotBlank
    @Schema(description = "CPF do usuario", example = "000.000.000-00", required = true)
    private String cpf;

    @NotBlank
    @Schema(description = "E-mail do usuario", example = "jose@gmail.com", required = true)
    private String email;

    @NotBlank
    @Schema(description = "Senha do usuario",example = "******", required = true)
    private String senha;

    @Schema(description = "Endereço do usuario",required = true)
    private Endereco endereco;

    @Column(length = 4000)
    private String observacao;


}