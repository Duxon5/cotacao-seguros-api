package com.banco.ficticio.cotacao.seguros.mockcatalogserver;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.Parameter.param;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.mockserver.client.MockServerClient;
import org.mockserver.mock.Expectation;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import com.banco.ficticio.cotacao.seguros.util.Util;

public class ProductExpectations {

	private MockServerClient mockServer = null;
	
	private final String endpointConsultarProduto = "/catalogo/produtos/consultarProduto";

	private final String productIdAtivoValido = "1b2da7cc-b367-4196-8a78-9cfeec21f587";
	private final String productIdInativoValido = "2b2da7cc-b367-4196-8a78-9cfeec21f587";
	
	public ProductExpectations(MockServerClient mockServer) {
		this.mockServer = mockServer;
	}

	public Expectation[][] expectations() throws IOException {

		Expectation[][] expectations = new Expectation[3][1];
	    
	    Expectation[] expectProdutoAtivoValido = mockServer.when(
	    		requestProdutoValido(productIdAtivoValido)
	    	).respond(
    			response200Produto("produto_valido_ativo")
	    	);
	    
	    Expectation[] expectProdutoInativoValido = mockServer.when(
	    		requestProdutoValido(productIdInativoValido)
	    	).respond(
    			response200Produto("produto_valido_inativo")
	    	);
	    
	    Expectation[] expectProdutoInvalido = mockServer.when(
	    		requestProdutoInvalido()
	    	).respond(
    			response400Produto("produto_invalido")
	    	);
	    
	    expectations[0] = expectProdutoAtivoValido;
	    expectations[1] = expectProdutoInativoValido;
	    expectations[2] = expectProdutoInvalido;
	    
	    return expectations;
	}
	
	private HttpRequest requestProdutoValido(String productId) {
		return request()
                .withMethod("GET")
                .withPath(endpointConsultarProduto)
                .withQueryStringParameters(
                        param("id", productId)
                );
	}

	private HttpRequest requestProdutoInvalido() {
	    String productIdsValidos = productIdAtivoValido+"|"+productIdInativoValido;
	    
	    String invalidoRegex = "^(?!("+productIdsValidos+")$).*";
	    
	    return request()
	            .withMethod("GET")
	            .withPath(endpointConsultarProduto)
	            .withQueryStringParameters(
	                    param("id", invalidoRegex)
	            );
	}

	private HttpResponse response200Produto(String arquivo) throws IOException {
		String json = Util.loadJsonResource("mock/"+arquivo+".json");
		
		return response().withStatusCode(HttpStatus.SC_OK).withBody(json);
	}

	private HttpResponse response400Produto(String arquivo) throws IOException {
		String json = Util.loadJsonResource("mock/"+arquivo+".json");
		
		return response().withStatusCode(HttpStatus.SC_BAD_REQUEST).withBody(json);
	}
	
}
