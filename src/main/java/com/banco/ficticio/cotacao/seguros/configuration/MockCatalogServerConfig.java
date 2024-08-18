package com.banco.ficticio.cotacao.seguros.configuration;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import java.io.IOException;

import org.mockserver.integration.ClientAndServer;
import org.mockserver.mock.Expectation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.banco.ficticio.cotacao.seguros.mockcatalogserver.CatalogServerExpectations;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Configuration
public class MockCatalogServerConfig {
	
	private static ClientAndServer mockCatalogServer = null;

	@Value("${api.catalog.host}")
	private String host;

	@Value("${api.catalog.port}")
	private Integer porta;
	
	@SuppressWarnings("deprecation")
	@PostConstruct
	public void startServer() throws IOException {
		System.out.println("Starting (Mocked) Catalog Server on Port "+porta);
		mockCatalogServer = startClientAndServer(porta);
		
		Expectation[][] expectations = new CatalogServerExpectations(host, porta).expectations();
		for (int i = 0; i < expectations.length; i++) {
			mockCatalogServer.sendExpectation(expectations[i]);
		}
		
	}

	@PreDestroy
	public void stopServer() {
		if (mockCatalogServer != null) {
			System.out.println("Destroying (Mocked) Catalog Server on Port "+porta);
			mockCatalogServer.stop();
		}
	}
	
}