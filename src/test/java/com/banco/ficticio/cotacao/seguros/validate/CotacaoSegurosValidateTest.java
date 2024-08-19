package com.banco.ficticio.cotacao.seguros.validate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.banco.ficticio.cotacao.seguros.dto.InsuranceQuoteDTO;
import com.banco.ficticio.cotacao.seguros.exception.CotacaoSegurosException;
import com.banco.ficticio.cotacao.seguros.mock.ObjetosPreenchidos;

@ExtendWith(MockitoExtension.class)
public class CotacaoSegurosValidateTest {

	@InjectMocks
    private CotacaoSegurosValidate validate;
	
	@BeforeAll
    public static void beforeAll() {
        MockitoAnnotations.openMocks(CotacaoSegurosValidateTest.class);
    }

	@Test
    public void solicitarCotacaoPreenchidoValidoTest() throws CotacaoSegurosException {
    	InsuranceQuoteDTO ins = ObjetosPreenchidos.insuranceQuoteDTOPreenchidoValido();
    	
    	validate.solicitarCotacao(ins);
    }
    
}