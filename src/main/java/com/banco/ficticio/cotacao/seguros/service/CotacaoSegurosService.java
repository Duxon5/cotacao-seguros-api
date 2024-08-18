package com.banco.ficticio.cotacao.seguros.service;

import com.banco.ficticio.cotacao.seguros.dto.InsurancePolicyDTO;
import com.banco.ficticio.cotacao.seguros.dto.InsuranceQuoteDTO;
import com.banco.ficticio.cotacao.seguros.exception.CotacaoSegurosException;

public interface CotacaoSegurosService {

	public Long solicitarCotacao(InsuranceQuoteDTO insuranceQuoteDTO) throws CotacaoSegurosException;
	public InsurancePolicyDTO consultarCotacao(Long insurancePolicyId) throws CotacaoSegurosException;
	
}
