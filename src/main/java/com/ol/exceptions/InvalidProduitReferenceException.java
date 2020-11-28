package com.ol.exceptions;

public class InvalidProduitReferenceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidProduitReferenceException(String errorMessage) {
		super(errorMessage);
	}
	
}
