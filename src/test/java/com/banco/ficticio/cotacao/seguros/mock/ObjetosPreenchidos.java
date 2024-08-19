package com.banco.ficticio.cotacao.seguros.mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banco.ficticio.cotacao.seguros.dto.CustomerDTO;
import com.banco.ficticio.cotacao.seguros.dto.InsuranceQuoteDTO;

public class ObjetosPreenchidos {

	public static Map<String, BigDecimal> mapCoveragesPreenchidoValido() {

		Map<String, BigDecimal> coverages = new HashMap<>();
        coverages.put("Incêndio", BigDecimal.valueOf(250000.00));
        coverages.put("Desastres naturais", BigDecimal.valueOf(500000.00));
        coverages.put("Responsabiliadade civil", BigDecimal.valueOf(75000.00));
        
        return coverages;
	}
	
	public static List<String> assistancesPreenchidoValido() {
        List<String> assistances = Arrays.asList("Encanador", "Eletricista", "Chaveiro 24h");
        return assistances;
	}
	
	public static CustomerDTO customerDTOPreenchidoValido() {
		CustomerDTO customer = new CustomerDTO(
                "36205578900",
                "John Wick",
                "NATURAL",
                "MALE",
                LocalDate.parse("1973-05-02"),
                "johnwick@gmail.com",
                11950503030L
            );
		
		return customer;
	}
	
	public static String productIdValido() {
		return "1b2da7cc-b367-4196-8a78-9cfeec21f587";
	}

	public static String productIdInvalido() {
		return "abcd1234";
	}
	
	public static String offerIdValido() {
		return "adc56d77-348c-4bf0-908f-22d402ee715c";
	}
	
	public static String offerIdInvalido() {
		return "abcd1234";
	}
	
	public static BigDecimal somatoriaCoverages(Map<String, BigDecimal> coverages) {
		BigDecimal sumCoverageAmount = BigDecimal.ZERO;
		
		for (Map.Entry<String, BigDecimal> entryInsurance : coverages.entrySet()) {
			BigDecimal valueInsurance = entryInsurance.getValue();
			sumCoverageAmount = sumCoverageAmount.add(valueInsurance);
		}
		
		return sumCoverageAmount;
	}
	
	public static InsuranceQuoteDTO insuranceQuoteDTOPreenchidoValido() {
        List<String> assistances = assistancesPreenchidoValido();
    	
        CustomerDTO customer = customerDTOPreenchidoValido();
        
        Map<String, BigDecimal> coverages = mapCoveragesPreenchidoValido();
        
        BigDecimal sumCoverages = somatoriaCoverages(coverages);
        
    	InsuranceQuoteDTO insuranceQuoteDTO = new InsuranceQuoteDTO(
    			productIdValido(), 
    			offerIdValido(), 
				"HOME", 
				new BigDecimal("75.25"), 
				sumCoverages, 
				coverages, 
				assistances, 
				customer);
    	
    	return insuranceQuoteDTO;
	}
	
	public static String jsonSolicitarCotacaoValido() {
    	return "{\r\n"
    			+ "	\"product_id\": \"1b2da7cc-b367-4196-8a78-9cfeec21f587\",\r\n"
    			+ "	\"offer_id\": \"adc56d77-348c-4bf0-908f-22d402ee715c\",\r\n"
    			+ "	\"category\": \"HOME\",\r\n"
    			+ "	\"total_monthly_premium_amount\": \"75.25\",\r\n"
    			+ "	\"total_coverage_amount\": 825000.00,\r\n"
    			+ "	\"coverages\": {\r\n"
    			+ "		\"Incêndio\": 250000.00,\r\n"
    			+ "		\"Desastres naturais\": 500000.00,\r\n"
    			+ "		\"Responsabiliadade civil\": 75000.00\r\n"
    			+ "	},\r\n"
    			+ "	\"assistances\": [\r\n"
    			+ "		\"Encanador\",\r\n"
    			+ "		\"Eletricista\",\r\n"
    			+ "		\"Chaveiro 24h\"\r\n"
    			+ "	],\r\n"
    			+ "	\"customer\": {\r\n"
    			+ "		\"document_number\": \"36205578900\",\r\n"
    			+ "		\"name\": \"John Wick\",\r\n"
    			+ "		\"type\": \"NATURAL\",\r\n"
    			+ "		\"gender\": \"MALE\",\r\n"
    			+ "		\"date_of_birth\": \"1973-05-02\",\r\n"
    			+ "		\"email\": \"johnwick@gmail.com\",\r\n"
    			+ "		\"phone_number\": 11950503030\r\n"
    			+ "	}\r\n"
    			+ "}";
    }
	
	public static String jsonSolicitarCotacaoInvalidoProdutoInvalido() {
    	return "{\r\n"
    			+ "	\"product_id\": \"abcd1234\",\r\n"
    			+ "	\"offer_id\": \"adc56d77-348c-4bf0-908f-22d402ee715c\",\r\n"
    			+ "	\"category\": \"HOME\",\r\n"
    			+ "	\"total_monthly_premium_amount\": \"75.25\",\r\n"
    			+ "	\"total_coverage_amount\": 825000.00,\r\n"
    			+ "	\"coverages\": {\r\n"
    			+ "		\"Incêndio\": 250000.00,\r\n"
    			+ "		\"Desastres naturais\": 500000.00,\r\n"
    			+ "		\"Responsabiliadade civil\": 75000.00\r\n"
    			+ "	},\r\n"
    			+ "	\"assistances\": [\r\n"
    			+ "		\"Encanador\",\r\n"
    			+ "		\"Eletricista\",\r\n"
    			+ "		\"Chaveiro 24h\"\r\n"
    			+ "	],\r\n"
    			+ "	\"customer\": {\r\n"
    			+ "		\"document_number\": \"36205578900\",\r\n"
    			+ "		\"name\": \"John Wick\",\r\n"
    			+ "		\"type\": \"NATURAL\",\r\n"
    			+ "		\"gender\": \"MALE\",\r\n"
    			+ "		\"date_of_birth\": \"1973-05-02\",\r\n"
    			+ "		\"email\": \"johnwick@gmail.com\",\r\n"
    			+ "		\"phone_number\": 11950503030\r\n"
    			+ "	}\r\n"
    			+ "}";
    }
	
	public static String jsonSolicitarCotacaoInvalidoTotalCoverageAmountCalcErrado() {
    	return "{\r\n"
    			+ "	\"product_id\": \"1b2da7cc-b367-4196-8a78-9cfeec21f587\",\r\n"
    			+ "	\"offer_id\": \"adc56d77-348c-4bf0-908f-22d402ee715c\",\r\n"
    			+ "	\"category\": \"HOME\",\r\n"
    			+ "	\"total_monthly_premium_amount\": \"75.25\",\r\n"
    			+ "	\"total_coverage_amount\": 925000.00,\r\n"
    			+ "	\"coverages\": {\r\n"
    			+ "		\"Incêndio\": 250000.00,\r\n"
    			+ "		\"Desastres naturais\": 500000.00,\r\n"
    			+ "		\"Responsabiliadade civil\": 75000.00\r\n"
    			+ "	},\r\n"
    			+ "	\"assistances\": [\r\n"
    			+ "		\"Encanador\",\r\n"
    			+ "		\"Eletricista\",\r\n"
    			+ "		\"Chaveiro 24h\"\r\n"
    			+ "	],\r\n"
    			+ "	\"customer\": {\r\n"
    			+ "		\"document_number\": \"36205578900\",\r\n"
    			+ "		\"name\": \"John Wick\",\r\n"
    			+ "		\"type\": \"NATURAL\",\r\n"
    			+ "		\"gender\": \"MALE\",\r\n"
    			+ "		\"date_of_birth\": \"1973-05-02\",\r\n"
    			+ "		\"email\": \"johnwick@gmail.com\",\r\n"
    			+ "		\"phone_number\": 11950503030\r\n"
    			+ "	}\r\n"
    			+ "}";
    }
	
	
}
