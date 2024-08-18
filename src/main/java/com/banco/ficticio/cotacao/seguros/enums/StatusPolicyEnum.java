package com.banco.ficticio.cotacao.seguros.enums;

public enum StatusPolicyEnum {

	CRIADO(0), 
	FINALIZADO(1);

	private Integer status;

	StatusPolicyEnum(int status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}