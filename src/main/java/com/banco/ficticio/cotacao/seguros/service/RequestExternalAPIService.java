package com.banco.ficticio.cotacao.seguros.service;

import com.banco.ficticio.cotacao.seguros.dto.OfferDTO;
import com.banco.ficticio.cotacao.seguros.dto.ProductDTO;

public interface RequestExternalAPIService {

	public ProductDTO consultarProduto(String productId) throws Exception;
	public OfferDTO consultarOferta(String offerId) throws Exception;
	
}
