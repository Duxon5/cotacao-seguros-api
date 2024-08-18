package com.banco.ficticio.cotacao.seguros.exception;

import com.banco.ficticio.cotacao.seguros.entity.ValidacaoEntity;

public class CotacaoSegurosException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private ValidacaoEntity validacao;

    public CotacaoSegurosException(ValidacaoEntity validacao) {
        super();
        this.validacao = validacao;
    }

    public CotacaoSegurosException(String message, ValidacaoEntity validacao) {
        super(message);
        this.validacao = validacao;
    }

    public CotacaoSegurosException(Throwable cause, ValidacaoEntity validacao) {
        super(cause);
        this.validacao = validacao;
    }

    public CotacaoSegurosException(String message, Throwable cause, ValidacaoEntity validacao) {
        super(message, cause);
        this.validacao = validacao;
    }

    public CotacaoSegurosException(CotacaoSegurosException e) {
        this(e.getCause(), e.getValidacao());
	}

	public ValidacaoEntity getValidacao() {
        return validacao;
    }

    public void setValidacao(ValidacaoEntity validacao) {
        this.validacao = validacao;
    }

}