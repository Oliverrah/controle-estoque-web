package com.made4you.controle.web.exceptions;

public class InsufficienteBalanceException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public InsufficienteBalanceException(String msg) {
		super(msg);
	}

}
