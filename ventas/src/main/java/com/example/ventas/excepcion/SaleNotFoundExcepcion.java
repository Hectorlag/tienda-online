package com.example.ventas.excepcion;

public class SaleNotFoundExcepcion extends RuntimeException{

    public SaleNotFoundExcepcion(String message){

        super(message);
    }
}
