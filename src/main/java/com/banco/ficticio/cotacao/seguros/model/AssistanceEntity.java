package com.banco.ficticio.cotacao.seguros.model;

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
@Table(name = "assistance")
public class AssistanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assistance_seq")
    @SequenceGenerator(name = "assistance_seq", sequenceName = "assistance_seq", allocationSize = 1)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_policy_id", nullable = false)
    @JsonBackReference
    private InsurancePolicyEntity insurancePolicy;

    @Column(name = "name")
    private String name;

	public AssistanceEntity() {
		super();
	}

	public AssistanceEntity(Long insurancePolicyId, String name) {
		this.insurancePolicy = new InsurancePolicyEntity(insurancePolicyId);
		this.name = name;
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

}