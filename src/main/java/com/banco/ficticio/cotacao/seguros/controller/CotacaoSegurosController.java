package com.banco.ficticio.cotacao.seguros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banco.ficticio.cotacao.seguros.dto.InsurancePolicyDTO;
import com.banco.ficticio.cotacao.seguros.dto.InsuranceQuoteDTO;
import com.banco.ficticio.cotacao.seguros.entity.ValidacaoEntity;
import com.banco.ficticio.cotacao.seguros.exception.CotacaoSegurosException;
import com.banco.ficticio.cotacao.seguros.log.Log;
import com.banco.ficticio.cotacao.seguros.service.CotacaoSegurosService;

@CrossOrigin(origins = {"http://localhost:4321"})
@RestController
@RequestMapping("/seguros")
public class CotacaoSegurosController {

	@Autowired
	private CotacaoSegurosService cotacaoSegurosService;

	private Log log = new Log(this.getClass());
	
	@PostMapping(value = "/solicitarCotacao", produces = "application/json")
	public ResponseEntity<?> solicitarCotacao(@RequestBody InsuranceQuoteDTO insuranceQuoteDTO) {
		ValidacaoEntity validacaoEntity = new ValidacaoEntity();
		
		try {
			Long insurancePolicyId = cotacaoSegurosService.solicitarCotacao(insuranceQuoteDTO);
			if(insurancePolicyId != null) {
				validacaoEntity.addMensagem("Apólice solicitada com sucesso. Utilize o código '"+insurancePolicyId+"' "
										  + "para consultar quando estiver disponível.");
				
				log.info("Sucesso - /solicitarCotacao | insurancePolicyId: " + insurancePolicyId);
			}
		} catch(CotacaoSegurosException ex) {
			if(ex.getValidacao().isVazio()) {
				log.error("/solicitarCotacao HttpStatus.INTERNAL_SERVER_ERROR | ex: " + ex);
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				log.warn("/solicitarCotacao validação: "+ex.getValidacao());
				return ResponseEntity.badRequest().body(ex.getValidacao());
			}
		}

		return new ResponseEntity<>(validacaoEntity, HttpStatus.OK);
	}

	@GetMapping(value="/consultarCotacao", produces = "application/json")
	public ResponseEntity<?> consultarCotacao(@RequestParam("id") Long id) {

		InsurancePolicyDTO insurancePolicyDTO = null;
		
		try {
			insurancePolicyDTO = cotacaoSegurosService.consultarCotacao(id);
			log.info("Sucesso - /consultarCotacao");
		} catch(CotacaoSegurosException ex) {
			if(ex.getValidacao().isVazio()) {
				log.error("/consultarCotacao HttpStatus.INTERNAL_SERVER_ERROR | ex: " + ex);
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				log.warn("/consultarCotacao validação: "+ex.getValidacao());
				return ResponseEntity.badRequest().body(ex.getValidacao());
			}
		}

		return new ResponseEntity<>(insurancePolicyDTO, HttpStatus.OK);
	}

	@GetMapping(value="/atualizarStatusParaFinalizado", produces = "application/json")
	public ResponseEntity<?> atualizarStatusParaFinalizado(@RequestParam("id") Long id) {

		try {
			cotacaoSegurosService.atualizarStatusParaFinalizado(id);
			log.info("Sucesso - /atualizarStatusParaFinalizado");
		} catch(CotacaoSegurosException ex) {
			if(ex.getValidacao().isVazio()) {
				log.error("/atualizarStatusParaFinalizado HttpStatus.INTERNAL_SERVER_ERROR | ex: " + ex);
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				log.warn("/atualizarStatusParaFinalizado validação: "+ex.getValidacao());
				return ResponseEntity.badRequest().body(ex.getValidacao());
			}
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
