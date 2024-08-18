package com.banco.ficticio.cotacao.seguros.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banco.ficticio.cotacao.seguros.model.AssistanceEntity;
import com.banco.ficticio.cotacao.seguros.model.CoverageEntity;
import com.banco.ficticio.cotacao.seguros.model.InsurancePolicyEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InsurancePolicyDTO {

	private Long insurancePolicyId;

	@JsonProperty("product_id")
	private String productId;
	@JsonProperty("offer_id")
	private String offerId;
	private String category;

	@JsonProperty("total_monthly_premium_amount")
	private BigDecimal totalMonthlyPremiumAmount;

	@JsonProperty("total_coverage_amount")
	private BigDecimal totalCoverageAmount;
	private Map<String, BigDecimal> coverages;
	private List<String> assistances;
	private CustomerDTO customer;

	@JsonProperty("created_at")
	private LocalDateTime createdAt;

	@JsonProperty("updated_at")
	private LocalDateTime updatedAt;
	
	public InsurancePolicyDTO(InsurancePolicyEntity insurancePolicyEntity) {
	    this.insurancePolicyId = insurancePolicyEntity.getInsurancePolicyId();
	    this.productId = insurancePolicyEntity.getProductId();
	    this.offerId = insurancePolicyEntity.getOfferId();
	    this.category = insurancePolicyEntity.getCategory();
	    this.totalMonthlyPremiumAmount = insurancePolicyEntity.getTotalMonthlyPremiumAmount();
	    this.totalCoverageAmount = insurancePolicyEntity.getTotalCoverageAmount();
	    this.coverages = new HashMap<String, BigDecimal>();;
	    this.assistances = new ArrayList<>();
	    this.customer = new CustomerDTO(insurancePolicyEntity.getCustomer());
	    this.createdAt = insurancePolicyEntity.getCreatedAt();
	    this.updatedAt = insurancePolicyEntity.getUpdatedAt();
	    
	    for (CoverageEntity coverageEntity : insurancePolicyEntity.getCoverages()) {
			String key = coverageEntity.getName();
			BigDecimal value = coverageEntity.getAmount();
			this.coverages.put(key, value);
		}
	    
	    for (AssistanceEntity assistanceEntity : insurancePolicyEntity.getAssistances()) {
			this.assistances.add(assistanceEntity.getName());
		}
	}

	public Long getInsurancePolicyId() {
		return insurancePolicyId;
	}

	public void setInsurancePolicyId(Long insurancePolicyId) {
		this.insurancePolicyId = insurancePolicyId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getTotalMonthlyPremiumAmount() {
		return totalMonthlyPremiumAmount;
	}

	public void setTotalMonthlyPremiumAmount(BigDecimal totalMonthlyPremiumAmount) {
		this.totalMonthlyPremiumAmount = totalMonthlyPremiumAmount;
	}

	public BigDecimal getTotalCoverageAmount() {
		return totalCoverageAmount;
	}

	public void setTotalCoverageAmount(BigDecimal totalCoverageAmount) {
		this.totalCoverageAmount = totalCoverageAmount;
	}

	public Map<String, BigDecimal> getCoverages() {
		return coverages;
	}

	public void setCoverages(Map<String, BigDecimal> coverages) {
		this.coverages = coverages;
	}

	public List<String> getAssistances() {
		return assistances;
	}

	public void setAssistances(List<String> assistances) {
		this.assistances = assistances;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}