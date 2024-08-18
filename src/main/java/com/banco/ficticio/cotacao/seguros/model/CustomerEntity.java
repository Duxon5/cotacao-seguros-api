package com.banco.ficticio.cotacao.seguros.model;

import java.time.LocalDate;

import com.banco.ficticio.cotacao.seguros.dto.CustomerDTO;
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
@Table(name = "customer")
public class CustomerEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "insurance_policy_id", nullable = false)
    @JsonBackReference
	private InsurancePolicyEntity insurancePolicy;

	@Column(name = "document_number")
    private String documentNumber;
	@Column(name = "name")
	private String name;
	@Column(name = "type")
	private String type;
	@Column(name = "gender")
	private String gender;
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	@Column(name = "email")
	private String email;
	@Column(name = "phone_number")
	private Long phoneNumber;

	public CustomerEntity() {
		super();
	}

	public CustomerEntity(Long insurancePolicyId, CustomerDTO customer) {
		this.insurancePolicy = new InsurancePolicyEntity(insurancePolicyId);
		this.documentNumber = customer.getDocumentNumber();
		this.name = customer.getName();
		this.type = customer.getType();
		this.gender = customer.getGender();
		this.dateOfBirth = customer.getDateOfBirth();
		this.email = customer.getEmail();
		this.phoneNumber = customer.getPhoneNumber();
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

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}