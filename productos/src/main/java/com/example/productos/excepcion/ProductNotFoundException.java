package com.example.productos.excepcion;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message) {

        super(message);
    }

}
