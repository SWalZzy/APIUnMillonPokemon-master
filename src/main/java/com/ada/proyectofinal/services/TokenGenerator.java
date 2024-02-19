package com.ada.proyectofinal.services;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service // Anotación que indica que la clase es un servicio
public class TokenGenerator {

    // Método para generar un token
    protected static SecureRandom random = new SecureRandom();


    // Método para generar un token
    public synchronized String generateToken( String username ) {
        long longToken = Math.abs( random.nextLong() );
        String random = Long.toString( longToken, 16 );
        return ( username + ":" + random );
    }
}