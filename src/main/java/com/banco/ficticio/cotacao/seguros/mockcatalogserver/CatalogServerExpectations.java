package com.banco.ficticio.cotacao.seguros.mockcatalogserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mockserver.client.MockServerClient;
import org.mockserver.mock.Expectation;

public class CatalogServerExpectations {

	private MockServerClient mockServer;
	
	public CatalogServerExpectations(String host, Integer porta) {
		this.mockServer = new MockServerClient(host, porta);
	}
	    
	public Expectation[][] expectations() throws IOException {

		List<Expectation[]> expectations = new ArrayList<>();
		
		ProductExpectations productExpectations = new ProductExpectations(mockServer);
		OfferExpectations offerExpectations = new OfferExpectations(mockServer);
		
		Expectation[][] productsExpectations = productExpectations.expectations();
		Expectation[][] offersExpectations = offerExpectations.expectations();

	    for (Expectation[] expectation : productsExpectations) {
	        expectations.add(expectation);
	    }

	    for (Expectation[] expectationsArray : offersExpectations) {
	        expectations.add(expectationsArray);
	    }

	    return expectations.toArray(new Expectation[0][0]);
	}
	
}