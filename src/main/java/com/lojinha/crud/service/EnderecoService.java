package com.lojinha.crud.service;

import org.springframework.web.client.RestTemplate;

import com.lojinha.crud.model.Endereco;

public class EnderecoService {

    public Endereco getEndereco( String cep) {
        String url = "https://brasilapi.com.br/api/cep/v2/"+cep;
        RestTemplate restTemplate = new RestTemplate();
        Endereco ob = restTemplate.getForObject(url, Endereco.class);
        return ob;
    }
}
