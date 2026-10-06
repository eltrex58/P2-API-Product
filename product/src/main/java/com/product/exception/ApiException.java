package com.product.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class ApiException extends RuntimeException {
    	private static final long serialVersionUID = 1L;
	    private HttpStatus status;
	
	    public ApiException(HttpStatus status, String message) {
	    	super(message);
    		this.status = status;
	    }
}
