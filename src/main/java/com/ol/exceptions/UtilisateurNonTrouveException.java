package com.ol.exceptions;

public class UtilisateurNonTrouveException extends Exception{
	 public UtilisateurNonTrouveException() {
	    }

	    public UtilisateurNonTrouveException(String message) {
	        super(message);
	    }

	    public UtilisateurNonTrouveException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public UtilisateurNonTrouveException(Throwable cause) {
	        super(cause);
	    }

	    public UtilisateurNonTrouveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	    }
}
