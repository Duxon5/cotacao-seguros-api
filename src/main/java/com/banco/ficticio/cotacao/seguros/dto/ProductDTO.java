package com.banco.ficticio.cotacao.seguros.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductDTO(
	String id,
	String name,
	@JsonProperty("created_at")
	LocalDateTime createdAt,
	boolean active,
	List<String> offers
) {

}

//public class ProductDTO {
//	private String id;
//	private String name;
//	private LocalDateTime createdAt;
//	private boolean active;
//	private List<String> offers;
//
//	public ProductDTO(String id, String name, LocalDateTime createdAt, boolean active, List<String> offers) {
//		this.id = id;
//		this.name = name;
//		this.createdAt = createdAt;
//		this.active = active;
//		this.offers = offers;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public boolean isActive() {
//		return active;
//	}
//
//	public void setActive(boolean active) {
//		this.active = active;
//	}
//
//	public List<String> getOffers() {
//		return offers;
//	}
//
//	public void setOffers(List<String> offers) {
//		this.offers = offers;
//	}
//
//}