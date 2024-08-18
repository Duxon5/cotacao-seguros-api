package com.banco.ficticio.cotacao.seguros.util;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	public static String loadJsonResource(String path) throws IOException {
	    // Obtendo o InputStream a partir do classpath
	    ClassLoader classLoader = Util.class.getClassLoader();
	    InputStream inputStream = classLoader.getResourceAsStream(path);

	    if (inputStream == null) {
	        throw new RuntimeException("Arquivo não encontrado nos resources: " + path);
	    }
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    Object produto = objectMapper.readValue(inputStream, Object.class);
	    return objectMapper.writeValueAsString(produto);
	}
}