package com.banco.ficticio.cotacao.seguros.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.banco.ficticio.cotacao.seguros.mock.ObjetosPreenchidos;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CotacaoSegurosControllerTest {

	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
	public void solicitarCotacaoValidoTest() throws Exception {
    	String json = ObjetosPreenchidos.jsonSolicitarCotacaoValido();
    	
		mockMvc.perform(MockMvcRequestBuilders.post("/seguros/solicitarCotacao")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

    @Test
	public void solicitarCotacaoProdutoInvalidoTest() throws Exception {
    	String json = ObjetosPreenchidos.jsonSolicitarCotacaoInvalidoProdutoInvalido();
    	
		mockMvc.perform(MockMvcRequestBuilders.post("/seguros/solicitarCotacao")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

    @Test
	public void solicitarCotacaoTotalCoverageAmountCalculadoErradoTest() throws Exception {
    	String json = ObjetosPreenchidos.jsonSolicitarCotacaoInvalidoTotalCoverageAmountCalcErrado();
    	
		mockMvc.perform(MockMvcRequestBuilders.post("/seguros/solicitarCotacao")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

}