package com.banco.ficticio.cotacao.seguros.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.banco.ficticio.cotacao.seguros.dto.OfferDTO;
import com.banco.ficticio.cotacao.seguros.dto.ProductDTO;
import com.banco.ficticio.cotacao.seguros.entity.ValidacaoEntity;
import com.banco.ficticio.cotacao.seguros.exception.CotacaoSegurosException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.transaction.Transactional;

@Transactional(rollbackOn=CotacaoSegurosException.class)
@Service
public class RequestExternalAPIServiceImpl implements RequestExternalAPIService {

	@Value("${api.catalog.consultar-produto.url}")
	private String endpointConsultarProduto;

	@Value("${api.catalog.consultar-oferta.url}")
	private String endpointConsultarOferta;

	private HttpResponse<String> httpGet(String uri)
			throws IOException, InterruptedException, CotacaoSegurosException, Exception {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).GET().build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		System.out.println("httpGet: "+uri);
		System.out.println(response.body());
		
		if(response.statusCode() == HttpStatus.SC_BAD_REQUEST) {
			ValidacaoEntity validacaoEntity = new ValidacaoEntity();
			validacaoEntity.parseResponseBody(response.body());
			throw new CotacaoSegurosException(validacaoEntity);
		} else if(response.statusCode() != HttpStatus.SC_OK) {
			throw new Exception(response.body());
		}
		
		return response;
	}
	
	@Override
	public ProductDTO consultarProduto(String productId) throws CotacaoSegurosException, Exception {
		String uri = endpointConsultarProduto + "?id=" + productId;

		HttpResponse<String> response = httpGet(uri);

	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.registerModule(new JavaTimeModule());
		
		return objectMapper.readValue(response.body(), ProductDTO.class);
	}

	@Override
	public OfferDTO consultarOferta(String offerId) throws CotacaoSegurosException, Exception {
		String uri = endpointConsultarOferta + "?id=" + offerId;

		HttpResponse<String> response = httpGet(uri);

	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.registerModule(new JavaTimeModule());
		
		return objectMapper.readValue(response.body(), OfferDTO.class);
	}

}
