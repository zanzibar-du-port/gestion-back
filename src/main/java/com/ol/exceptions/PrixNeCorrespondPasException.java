package com.ol.exceptions;

public class PrixNeCorrespondPasException extends Exception{

	public PrixNeCorrespondPasException() {
    }

    public PrixNeCorrespondPasException(String message) {
        super(message);
    }

    public PrixNeCorrespondPasException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrixNeCorrespondPasException(Throwable cause) {
        super(cause);
    }

    public PrixNeCorrespondPasException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
