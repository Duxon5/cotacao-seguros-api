package com.banco.ficticio.cotacao.seguros.runner;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@SelectPackages({
	"com.banco.ficticio.cotacao.seguros.service", 
	"com.banco.ficticio.cotacao.seguros.controller",
	"com.banco.ficticio.cotacao.seguros.validate"
})
@Suite
public class TestSuite {

}