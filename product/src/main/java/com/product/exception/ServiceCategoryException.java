package com.product.exception;

/**
 * Excepción personalizada para errores relacionados con el servicio de categorías.
 */
public class ServiceCategoryException extends RuntimeException {
    
    /**
     * Constructor por defecto de la excepción.
     */
    public ServiceCategoryException(){
        super("Hubo algún problema al consultar las categorías");
    }

    /**
     * Constructor de la excepción con un mensaje específico.
     * @param msg El mensaje de error.
     */
    public ServiceCategoryException(String msg){
        super(msg);
    }

    /**
     * Constructor de la excepción con un mensaje específico y una causa.
     * @param msg El mensaje de error.
     * @param cause La causa de la excepción.
     */
    public ServiceCategoryException(String msg, Throwable cause){
        super(msg, cause);
    }
}
