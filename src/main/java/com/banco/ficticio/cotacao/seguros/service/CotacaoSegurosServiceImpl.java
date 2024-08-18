package com.banco.ficticio.cotacao.seguros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.ficticio.cotacao.seguros.dao.CotacaoSegurosDAO;
import com.banco.ficticio.cotacao.seguros.dto.InsurancePolicyDTO;
import com.banco.ficticio.cotacao.seguros.dto.InsuranceQuoteDTO;
import com.banco.ficticio.cotacao.seguros.dto.OfferDTO;
import com.banco.ficticio.cotacao.seguros.dto.ProductDTO;
import com.banco.ficticio.cotacao.seguros.entity.ValidacaoEntity;
import com.banco.ficticio.cotacao.seguros.exception.CotacaoSegurosException;
import com.banco.ficticio.cotacao.seguros.model.AssistanceEntity;
import com.banco.ficticio.cotacao.seguros.model.CoverageEntity;
import com.banco.ficticio.cotacao.seguros.model.InsurancePolicyEntity;
import com.banco.ficticio.cotacao.seguros.validate.CotacaoSegurosValidate;

import jakarta.transaction.Transactional;

@Transactional(rollbackOn=CotacaoSegurosException.class)
@Service
public class CotacaoSegurosServiceImpl implements CotacaoSegurosService {

	@Autowired
	private CotacaoSegurosDAO cotacaoSegurosDAO;
	
	@Autowired
	private CotacaoSegurosValidate validate;

	@Autowired
	private RequestExternalAPIService requestExternalAPI;
	
	@Override
	public Long solicitarCotacao(InsuranceQuoteDTO insuranceQuoteDTO) throws CotacaoSegurosException {

		ProductDTO productDTO = null;
		OfferDTO offerDTO = null;
		
		try {
			validate.solicitarCotacao(insuranceQuoteDTO);
			
			productDTO = consultarProduto(insuranceQuoteDTO);
			validate.consultarProduto(insuranceQuoteDTO, productDTO);
			
			offerDTO = consultarOferta(insuranceQuoteDTO);
			validate.consultarOferta(insuranceQuoteDTO, offerDTO);
		} catch(CotacaoSegurosException e) {
			throw new CotacaoSegurosException(e);
		}
		
		InsurancePolicyEntity insurancePolicyEntity = null;
		
		try {
			insurancePolicyEntity = new InsurancePolicyEntity(insuranceQuoteDTO);
			cotacaoSegurosDAO.incluirInsurancePolicy(insurancePolicyEntity);

			insurancePolicyEntity.setChilds(insuranceQuoteDTO);
			cotacaoSegurosDAO.incluirCustomer(insurancePolicyEntity.getCustomer());
			
			for (CoverageEntity coverageEntity : insurancePolicyEntity.getCoverages()) {
				cotacaoSegurosDAO.incluirCoverage(coverageEntity);
			}
			
			for (AssistanceEntity assistanceEntity : insurancePolicyEntity.getAssistances()) {
				cotacaoSegurosDAO.incluirAssistance(assistanceEntity);
			}
		} catch(Exception e) {
			ValidacaoEntity validacaoEntity = new ValidacaoEntity();
			validacaoEntity.addMensagem("Erro ao persistir no banco de dados!");
			validacaoEntity.addMensagem("Exceção: " + e.getMessage());
			throw new CotacaoSegurosException(validacaoEntity);
		}
		
		return insurancePolicyEntity.getInsurancePolicyId();
	}
	
	@Override
	public InsurancePolicyDTO consultarCotacao(Long insurancePolicyId) throws CotacaoSegurosException {
		InsurancePolicyDTO insurancePolicyDTO = null;
		
		try {
			InsurancePolicyEntity insurancePolicyEntity = cotacaoSegurosDAO.buscarPorInsurancePolicyId(insurancePolicyId);
			validate.consultarCotacao(insurancePolicyEntity);
			
			insurancePolicyDTO = new InsurancePolicyDTO(insurancePolicyEntity);
		} catch(CotacaoSegurosException e) {
			throw new CotacaoSegurosException(e);
		}
		
		return insurancePolicyDTO;
	}

	private ProductDTO consultarProduto(
			InsuranceQuoteDTO insuranceQuoteDTO
	) throws CotacaoSegurosException {
		ProductDTO productDTO = null;
		
		try {
			productDTO = requestExternalAPI.consultarProduto(insuranceQuoteDTO.productId());
		} catch (CotacaoSegurosException e) {
			throw new CotacaoSegurosException(e);
		} catch (Exception e) {
			ValidacaoEntity validacaoEntity = new ValidacaoEntity();
			validacaoEntity.addMensagem(e.getMessage());
			throw new CotacaoSegurosException(validacaoEntity);
		}
		
		return productDTO;
	}
	
	private OfferDTO consultarOferta(
			InsuranceQuoteDTO insuranceQuoteDTO
	) throws CotacaoSegurosException {
		OfferDTO offerDTO = null;
		
		try {
			offerDTO = requestExternalAPI.consultarOferta(insuranceQuoteDTO.offerId());
		} catch (CotacaoSegurosException e) {
			throw new CotacaoSegurosException(e);
		} catch (Exception e) {
			ValidacaoEntity validacaoEntity = new ValidacaoEntity();
			validacaoEntity.addMensagem(e.getMessage());
			throw new CotacaoSegurosException(validacaoEntity);
		}
		
		return offerDTO;
	}

}
