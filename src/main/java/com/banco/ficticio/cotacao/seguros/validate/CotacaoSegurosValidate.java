package com.banco.ficticio.cotacao.seguros.validate;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.banco.ficticio.cotacao.seguros.dto.InsuranceQuoteDTO;
import com.banco.ficticio.cotacao.seguros.dto.MonthlyPremiumAmountDTO;
import com.banco.ficticio.cotacao.seguros.dto.OfferDTO;
import com.banco.ficticio.cotacao.seguros.dto.ProductDTO;
import com.banco.ficticio.cotacao.seguros.entity.ValidacaoEntity;
import com.banco.ficticio.cotacao.seguros.enums.StatusPolicyEnum;
import com.banco.ficticio.cotacao.seguros.exception.CotacaoSegurosException;
import com.banco.ficticio.cotacao.seguros.model.InsurancePolicyEntity;

@Service
public class CotacaoSegurosValidate {

	public void solicitarCotacao(InsuranceQuoteDTO insuranceQuoteDTO) throws CotacaoSegurosException {
		ValidacaoEntity validacaoEntity = new ValidacaoEntity();
		
		if(insuranceQuoteDTO.productId() == null || insuranceQuoteDTO.productId().isEmpty()) {
			validacaoEntity.addMensagem("Id do Produto em branco!");
		}

		if(insuranceQuoteDTO.offerId() == null || insuranceQuoteDTO.offerId().isEmpty()) {
			validacaoEntity.addMensagem("Id da Oferta em branco!");
		}
		
		if(!validacaoEntity.isVazio()) {
			throw new CotacaoSegurosException(validacaoEntity);
		}
	}

	public ValidacaoEntity consultarProduto(InsuranceQuoteDTO insuranceQuoteDTO, ProductDTO productDTO) throws CotacaoSegurosException {

		ValidacaoEntity validacaoEntity = new ValidacaoEntity();
		
		if(!productDTO.active()) {
			validacaoEntity.addMensagem("O produto não está mais ativo.");
		}
		
		if(productDTO.offers() == null || productDTO.offers().isEmpty()) {
			validacaoEntity.addMensagem("O produto não possui ofertas.");
			throw new CotacaoSegurosException(validacaoEntity);
		}
		
		if(!productDTO.offers().contains(insuranceQuoteDTO.offerId())) {
			validacaoEntity.addMensagem("O produto não possui a oferta informada.");
		}
		
		if(!validacaoEntity.isVazio()) {
			throw new CotacaoSegurosException(validacaoEntity);
		}
		
		return validacaoEntity;
	}

	public ValidacaoEntity consultarOferta(InsuranceQuoteDTO insuranceQuoteDTO, OfferDTO offerDTO) throws CotacaoSegurosException {

		ValidacaoEntity validacaoEntity = new ValidacaoEntity();
		
		if(!offerDTO.active()) {
			validacaoEntity.addMensagem("A oferta não está mais ativa.");
			throw new CotacaoSegurosException(validacaoEntity);
		}
		
		/*
		 * As coberturas informadas estão:
		 * - dentro da lista de coberturas da oferta
		 * - abaixo dos valores máximos permitidos.
		 */		
		if(insuranceQuoteDTO.coverages() != null && offerDTO.coverages() != null) {
			for (Map.Entry<String, BigDecimal> entryInsurance : insuranceQuoteDTO.coverages().entrySet()) {
				String keyInsurance = entryInsurance.getKey();
				BigDecimal valueInsurance = entryInsurance.getValue();
				
				Map.Entry<String, BigDecimal> entryOfferEncontrada = null;
				for (Map.Entry<String, BigDecimal> entryOffer : offerDTO.coverages().entrySet()) {
					String keyOffer = entryOffer.getKey();
					
					if(keyInsurance.equals(keyOffer)) {
						entryOfferEncontrada = entryOffer;
						break;
					}
				}
				
				if(entryOfferEncontrada == null) {
					validacaoEntity.addMensagem("A cobertura '"+keyInsurance+"' não está disponível na oferta informada.");
				} else {
					BigDecimal valueOffer = entryOfferEncontrada.getValue();
					
					if(valueInsurance.compareTo(valueOffer) > 0) {
						validacaoEntity.addMensagem("A cobertura '"+keyInsurance+"' possui valor maior do que a oferta disponível '"+valueOffer+"'.");
					}
				}
			}
		}

		// As assistências informadas estão dentro da lista de assistências da oferta
		if(insuranceQuoteDTO.assistances() != null && offerDTO.assistances() != null) {
			for (String assistance : insuranceQuoteDTO.assistances()) {
				if(!offerDTO.assistances().contains(assistance)) {
					validacaoEntity.addMensagem("A assistência '"+assistance+"' não está disponível na oferta informada.");
				}
			}
		}
		
		// O valor total do prêmio mensal está entre o máximo e mínimo definido para a oferta
		MonthlyPremiumAmountDTO monthlyPremiumAmountDTO = offerDTO.monthlyPremiumAmount();
		if (insuranceQuoteDTO.totalMonthlyPremiumAmount() != null && monthlyPremiumAmountDTO != null &&
			monthlyPremiumAmountDTO.minAmount() != null && monthlyPremiumAmountDTO.maxAmount() != null) {
			
			boolean isIgualOuMaior = insuranceQuoteDTO.totalMonthlyPremiumAmount().compareTo(monthlyPremiumAmountDTO.minAmount()) >= 0;
			boolean isIgualOuMenor = insuranceQuoteDTO.totalMonthlyPremiumAmount().compareTo(monthlyPremiumAmountDTO.maxAmount()) <= 0;
			
			if(!(isIgualOuMaior && isIgualOuMenor)) {
				validacaoEntity.addMensagem("O valor total do prêmio mensal deve estar entre '"+monthlyPremiumAmountDTO.minAmount()+"' "
										  + "e '"+monthlyPremiumAmountDTO.maxAmount()+"'");
			}
			
		}

		// O valor total das coberturas corresponde a somatória das coberturas informadas
		if(insuranceQuoteDTO.coverages() != null) {
			BigDecimal totalCoverageAmount = insuranceQuoteDTO.totalCoverageAmount();
			BigDecimal sumCoverageAmount = new BigDecimal(0);

			for (Map.Entry<String, BigDecimal> entryInsurance : insuranceQuoteDTO.coverages().entrySet()) {
				BigDecimal valueInsurance = entryInsurance.getValue();
				sumCoverageAmount = sumCoverageAmount.add(valueInsurance);
			}
			
			if(totalCoverageAmount.compareTo(sumCoverageAmount) != 0) {
				validacaoEntity.addMensagem("O valor total ("+totalCoverageAmount+") das coberturas corresponde a "
										  + "somatória das coberturas informadas ("+sumCoverageAmount+")");
			}
		}
		
		if(!validacaoEntity.isVazio()) {
			throw new CotacaoSegurosException(validacaoEntity);
		}
		
		return validacaoEntity;
	}

	public ValidacaoEntity consultarCotacao(InsurancePolicyEntity insurancePolicyEntity) throws CotacaoSegurosException {

		ValidacaoEntity validacaoEntity = new ValidacaoEntity();
		
		if(insurancePolicyEntity == null) {
			validacaoEntity.addMensagem("Não existe apólice com este id.");
			throw new CotacaoSegurosException(validacaoEntity);
		}
		
		if(insurancePolicyEntity.getRequestStatus() == StatusPolicyEnum.CRIADO) {
			validacaoEntity.addMensagem("Esta apólice ainda não foi finalizada, consulte novamente mais tarde.");
			throw new CotacaoSegurosException(validacaoEntity);
		}
		
		return validacaoEntity;
	}

	public ValidacaoEntity atualizarStatusParaFinalizado(
			Long insurancePolicyId, 
			InsurancePolicyEntity insurancePolicyEntity
	) throws CotacaoSegurosException  {
		
		ValidacaoEntity validacaoEntity = new ValidacaoEntity();
		
		if(insurancePolicyId == null) {
			validacaoEntity.addMensagem("O id da apólice não foi informado.");
			throw new CotacaoSegurosException(validacaoEntity);
		}
		
		if(insurancePolicyEntity == null) {
			validacaoEntity.addMensagem("Não existe apólice com este id.");
			throw new CotacaoSegurosException(validacaoEntity);
		}
		
		if(insurancePolicyEntity.getRequestStatus() == StatusPolicyEnum.FINALIZADO) {
			validacaoEntity.addMensagem("Esta apólice já consta como finalizada.");
			throw new CotacaoSegurosException(validacaoEntity);
		}
		
		return validacaoEntity;
	}

}
