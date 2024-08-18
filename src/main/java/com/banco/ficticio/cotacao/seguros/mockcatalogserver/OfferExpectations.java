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

public class OfferExpectations {

	private MockServerClient mockServer = null;
	
	private final String endpointConsultarOferta = "/catalogo/produtos/consultarOferta";

	private final String offerIdAtivoValido = "adc56d77-348c-4bf0-908f-22d402ee715c";
	private final String offerIdInativoValido = "bdc56d77-348c-4bf0-908f-22d402ee715c";
	
	public OfferExpectations(MockServerClient mockServer) {
		this.mockServer = mockServer;
	}

	public Expectation[][] expectations() throws IOException {

		Expectation[][] expectations = new Expectation[3][1];
	    
	    Expectation[] expectOfertaAtivoValido = mockServer.when(
	    		requestOfertaValida(offerIdAtivoValido)
			).respond(
				response200Oferta("oferta_valida_ativo")
			);
	    
	    Expectation[] expectOfertaInativoValido = mockServer.when(
	    		requestOfertaValida(offerIdInativoValido)
	    	).respond(
    			response200Oferta("oferta_valida_inativo")
	    	);
	    
	    Expectation[] expectOfertaInvalido = mockServer.when(
	    		requestOfertaInvalida()
	    	).respond(
    			response400Oferta("oferta_invalida")
	    	);
	    
	    expectations[0] = expectOfertaAtivoValido;
	    expectations[1] = expectOfertaInativoValido;
	    expectations[2] = expectOfertaInvalido;
	    
	    return expectations;
	}
	
	private HttpRequest requestOfertaValida(String offerId) {
		return request()
                .withMethod("GET")
                .withPath(endpointConsultarOferta)
                .withQueryStringParameters(
                        param("id", offerId)
                );
	}
	
	private HttpRequest requestOfertaInvalida() {
	    String offerIdsValidos = offerIdAtivoValido+"|"+offerIdInativoValido;
	    
	    String invalidoRegex = "^(?!("+offerIdsValidos+")$).*";
	    
	    return request()
	            .withMethod("GET")
	            .withPath(endpointConsultarOferta)
	            .withQueryStringParameters(
	                    param("id", invalidoRegex)
	            );
	}

	private HttpResponse response200Oferta(String arquivo) throws IOException {
		String json = Util.loadJsonResource("mock/"+arquivo+".json");

		return response().withStatusCode(HttpStatus.SC_OK).withBody(json);
	}

	private HttpResponse response400Oferta(String arquivo) throws IOException {
		String json = Util.loadJsonResource("mock/"+arquivo+".json");

		return response().withStatusCode(HttpStatus.SC_BAD_REQUEST).withBody(json);
	}

}
