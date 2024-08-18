package com.banco.ficticio.cotacao.seguros.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidacaoEntity {

    private List<String> mensagens;
    
	public ValidacaoEntity(List<String> mensagens) {
        this.mensagens = mensagens;
    }

    public ValidacaoEntity() {
	}
    
	public List<String> getMensagens() {
        return mensagens;
    }

	public void addMensagem(String mensagem) {
		if(this.mensagens == null) {
			mensagens = new ArrayList<>();
		}
		
		mensagens.add(mensagem);
	}
	
	public void parseResponseBody(String body) throws IOException {
	    ObjectMapper objectMapper = new ObjectMapper();
	    ValidacaoEntity validacaoEntity = objectMapper.readValue(body, ValidacaoEntity.class);
	    setAll(validacaoEntity);
	}
	
    public void setAll(ValidacaoEntity validacaoEntity) {
    	this.mensagens = validacaoEntity.getMensagens();
    }

	@JsonIgnore
	public boolean isVazio() {
		return this.mensagens == null || this.mensagens.isEmpty();
	}

	@Override
	public String toString() {
		return mensagens.stream().collect(Collectors.joining(", "));
	}
	
}
