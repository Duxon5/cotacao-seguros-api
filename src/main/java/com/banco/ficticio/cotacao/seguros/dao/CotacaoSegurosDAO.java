package com.banco.ficticio.cotacao.seguros.dao;

import java.util.Optional;

import com.banco.ficticio.cotacao.seguros.model.AssistanceEntity;
import com.banco.ficticio.cotacao.seguros.model.CoverageEntity;
import com.banco.ficticio.cotacao.seguros.model.CustomerEntity;
import com.banco.ficticio.cotacao.seguros.model.InsurancePolicyEntity;

public interface CotacaoSegurosDAO {

	public Long generateId();
	public void incluirInsurancePolicy(InsurancePolicyEntity insurancePolicyEntity);
	public Optional<InsurancePolicyEntity> obterPorId(Long insurancePolicyEntity);
	public void incluirCustomer(CustomerEntity customerEntity);
	public void incluirCoverage(CoverageEntity customerEntity);
	public void incluirAssistance(AssistanceEntity assistanceEntity);
	public InsurancePolicyEntity buscarPorInsurancePolicyId(Long insurancePolicyId);
	public void atualizarStatusParaFinalizado(Long id);

}
