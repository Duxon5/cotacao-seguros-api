package com.banco.ficticio.cotacao.seguros.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banco.ficticio.cotacao.seguros.dto.InsuranceQuoteDTO;
import com.banco.ficticio.cotacao.seguros.enums.StatusPolicyEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "insurance_policy")
public class InsurancePolicyEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "insurance_policy_seq")
    @SequenceGenerator(name = "insurance_policy_seq", sequenceName = "insurance_policy_seq", allocationSize = 1)
    @Column(name = "insurance_policy_id")
    private Long insurancePolicyId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "offer_id")
    private String offerId;

    @Column(name = "category")
    private String category;

    @Column(name = "total_monthly_premium_amount", precision = 19, scale = 2)
    private BigDecimal totalMonthlyPremiumAmount;

    @Column(name = "total_coverage_amount", precision = 19, scale = 2)
    private BigDecimal totalCoverageAmount;

    @OneToMany(mappedBy = "insurancePolicy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CoverageEntity> coverages;

    @OneToMany(mappedBy = "insurancePolicy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AssistanceEntity> assistances;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @JsonManagedReference
    private CustomerEntity customer;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "request_status")
    private StatusPolicyEnum requestStatus;

	public InsurancePolicyEntity() {
		super();
	}
	
	public InsurancePolicyEntity(Long insurancePolicyId) {
		this.insurancePolicyId = insurancePolicyId;
	}
	
	public InsurancePolicyEntity(InsuranceQuoteDTO insuranceQuoteDTO) {
	    this.productId = insuranceQuoteDTO.productId();
	    this.offerId = insuranceQuoteDTO.offerId();
	    this.category = insuranceQuoteDTO.category();
	    this.totalMonthlyPremiumAmount = insuranceQuoteDTO.totalMonthlyPremiumAmount();
	    this.totalCoverageAmount = insuranceQuoteDTO.totalCoverageAmount();
	    this.requestStatus = StatusPolicyEnum.CRIADO;
	}

	public void setChilds(InsuranceQuoteDTO insuranceQuoteDTO) {
		this.coverages = new ArrayList<CoverageEntity>();
	    this.assistances = new ArrayList<AssistanceEntity>();
	    this.customer = new CustomerEntity(insurancePolicyId, insuranceQuoteDTO.customer());

	    for (Map.Entry<String, BigDecimal> entryCoverage : insuranceQuoteDTO.coverages().entrySet()) {
			CoverageEntity coverageEntity = new CoverageEntity(insurancePolicyId, entryCoverage);
			this.coverages.add(coverageEntity);
		}
	    
	    for (String assistance : insuranceQuoteDTO.assistances()) {
			AssistanceEntity assistanceEntity = new AssistanceEntity(insurancePolicyId, assistance);
			this.assistances.add(assistanceEntity);
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

	public List<CoverageEntity> getCoverages() {
		return coverages;
	}

	public void setCoverages(List<CoverageEntity> coverages) {
		this.coverages = coverages;
	}

	public List<AssistanceEntity> getAssistances() {
		return assistances;
	}

	public void setAssistances(List<AssistanceEntity> assistances) {
		this.assistances = assistances;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
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

	public StatusPolicyEnum getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(StatusPolicyEnum requestStatus) {
		this.requestStatus = requestStatus;
	}
    
}