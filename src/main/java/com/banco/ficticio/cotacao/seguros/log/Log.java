package com.banco.ficticio.cotacao.seguros.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

	private final Logger logger;
	
	public Log(Class<?> classe) {
		this.logger = LoggerFactory.getLogger(classe);
	}

	public void info(String msg) {
		logger.info(msg);
	}
	
	public void error(String msg) {
		logger.error(msg);
	}
	
	public void warn(String msg) {
		logger.warn(msg);
	}
	
}
