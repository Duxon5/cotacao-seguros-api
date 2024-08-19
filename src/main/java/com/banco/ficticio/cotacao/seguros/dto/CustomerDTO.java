package com.banco.ficticio.cotacao.seguros.dto;

import java.time.LocalDate;

import com.banco.ficticio.cotacao.seguros.model.CustomerEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDTO {
	@JsonProperty("document_number")
	private String documentNumber;
	private String name;
	private String type;
	private String gender;
	@JsonProperty("date_of_birth")
	private LocalDate dateOfBirth;
	String email;
	@JsonProperty("phone_number")
	private Long phoneNumber;
	
	public CustomerDTO() {
		super();
	}

	public CustomerDTO(CustomerEntity customerEntity) {
		this.documentNumber = customerEntity.getDocumentNumber();
		this.name = customerEntity.getName();
		this.type = customerEntity.getType();
		this.gender = customerEntity.getGender();
		this.dateOfBirth = customerEntity.getDateOfBirth();
		this.email = customerEntity.getEmail();
		this.phoneNumber = customerEntity.getPhoneNumber();
	}

	public CustomerDTO(String documentNumber, String name, String type, String gender, LocalDate dateOfBirth,
			String email, Long phoneNumber) {
		super();
		this.documentNumber = documentNumber;
		this.name = name;
		this.type = type;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phoneNumber = phoneNumber;
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