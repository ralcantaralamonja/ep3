package com.ep3.grupo5.exception;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(Long id){
        super(String.format("Cliente con id : %s no registrado en la base de datos", id));
    }
}
