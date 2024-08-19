package com.banco.ficticio.cotacao.seguros.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.mock.Expectation;
import org.springframework.test.util.ReflectionTestUtils;

import com.banco.ficticio.cotacao.seguros.exception.CotacaoSegurosException;
import com.banco.ficticio.cotacao.seguros.mock.ObjetosPreenchidos;
import com.banco.ficticio.cotacao.seguros.mockcatalogserver.CatalogServerExpectations;

import jakarta.annotation.Resource;

@ExtendWith(MockitoExtension.class)
public class RequestExternalAPIServiceTest {

	@Resource
	@InjectMocks
    private RequestExternalAPIServiceImpl service;

	@BeforeAll
    public static void beforeAll() {
        MockitoAnnotations.openMocks(RequestExternalAPIServiceTest.class);
    }
	
	private static ClientAndServer mockServer;

	@SuppressWarnings("deprecation")
	@BeforeAll
    public static void startMockServer() throws IOException {
        mockServer = startClientAndServer(4322);
        
        Expectation[][] expectations = new CatalogServerExpectations("localhost", 4322).expectations();
		for (int i = 0; i < expectations.length; i++) {
			mockServer.sendExpectation(expectations[i]);
		}
    }
	
	@AfterAll
	public static void destroyMockServer() {
		if(mockServer != null)
			mockServer.stop();
	}

	public void doReflection() {
		ReflectionTestUtils.setField(service, "endpointConsultarProduto", "http://localhost:4322/catalogo/produtos/consultarProduto");
		ReflectionTestUtils.setField(service, "endpointConsultarOferta", "http://localhost:4322/catalogo/produtos/consultarOferta");
	}
    
    @Test
    public void consultarProdutoValido() throws Exception {
    	doReflection();
    	
    	String productId = ObjetosPreenchidos.productIdValido();
    	service.consultarProduto(productId);
    }
    
    @Test
    public void consultarProdutoInvalido() {
    	doReflection();
    	
    	String productId = ObjetosPreenchidos.productIdInvalido();
    	assertThrows(CotacaoSegurosException.class, () -> service.consultarProduto(productId));
    }
    
    @Test
    public void consultarOfertaValida() throws Exception {
    	doReflection();
    	
    	String offerId = ObjetosPreenchidos.offerIdValido();
    	service.consultarOferta(offerId);
    }
    
    @Test
    public void consultarOfertaInvalida() {
    	doReflection();

    	String offerId = ObjetosPreenchidos.offerIdInvalido();
    	assertThrows(CotacaoSegurosException.class, () -> service.consultarOferta(offerId));
    }
}