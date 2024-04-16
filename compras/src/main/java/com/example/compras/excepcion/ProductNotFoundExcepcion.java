package com.example.compras.excepcion;

public class ProductNotFoundExcepcion extends RuntimeException{

    public ProductNotFoundExcepcion (String message){

        super(message);
    }
}
