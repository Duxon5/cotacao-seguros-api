package com.banco.ficticio.cotacao.seguros.model;

import java.math.BigDecimal;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "coverage")
public class CoverageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coverage_seq")
    @SequenceGenerator(name = "coverage_seq", sequenceName = "coverage_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_policy_id", nullable = false)
    @JsonBackReference
    private InsurancePolicyEntity insurancePolicy;

    @Column(name = "name")
    private String name;

    @Column(name = "amount", precision = 19, scale = 2)
    private BigDecimal amount;

	public CoverageEntity() {
		super();
	}

	public CoverageEntity(Long insurancePolicyId, Entry<String, BigDecimal> entryCoverage) {
		this.insurancePolicy = new InsurancePolicyEntity(insurancePolicyId);
		this.name = entryCoverage.getKey();
		this.amount = entryCoverage.getValue();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InsurancePolicyEntity getInsurancePolicy() {
		return insurancePolicy;
	}

	public void setInsurancePolicy(InsurancePolicyEntity insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}