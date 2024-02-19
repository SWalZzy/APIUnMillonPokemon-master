package com.ada.proyectofinal.restcontrollers;

import com.ada.proyectofinal.entities.Entrenador;
import com.ada.proyectofinal.services.DataInicializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Anotación que indica que la clase es un controlador de una API REST
public class MainController {
    @Autowired // Inyección de dependencias
    private DataInicializer dataInicializer;

    @PostMapping("/generar") // Anotación que indica que el método maneja las peticiones POST
    public ResponseEntity<String> generarDatos(@RequestBody Entrenador entrenador){ // Método que genera los datos iniciales
        dataInicializer.GenerarEntrenadores(entrenador);
        dataInicializer.generarPokemons();
        dataInicializer.asignarPokemons();
        dataInicializer.prepararMercado();
        dataInicializer.generarAlineacion();
        dataInicializer.asignarAlineaciones();
        return new ResponseEntity<>("Funciona", HttpStatus.OK);
    }
}
