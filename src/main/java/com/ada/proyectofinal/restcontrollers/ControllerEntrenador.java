package com.ada.proyectofinal.restcontrollers;

import ch.qos.logback.core.model.Model;
import com.ada.proyectofinal.entities.Alineacion;
import com.ada.proyectofinal.entities.Entrenador;
import com.ada.proyectofinal.entities.Pokemon;
import com.ada.proyectofinal.services.ServicioEntrenador;
import com.ada.proyectofinal.services.ServicioMercado;
import com.ada.proyectofinal.services.ServicioPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController // Anotación que indica que la clase es un controlador de una API REST
@RequestMapping("/entrenador") // Anotación que indica la URL base para todas las peticiones
public class ControllerEntrenador {
    @Autowired // Inyección de dependencias
    private ServicioEntrenador servicioEntrenador;

    @GetMapping("/{id}") // Anotación que indica que el método maneja las peticiones GET
    public ResponseEntity<Optional> getPerfil(@PathVariable int id) { // Método que devuelve el perfil de un entrenador
        Optional<Entrenador> entrenador = Optional.ofNullable(servicioEntrenador.findById(id));
        return new ResponseEntity<>(entrenador, HttpStatus.OK);
    }

    @GetMapping("/{id}/alineacion") // Anotación que indica que el método maneja las peticiones GET
    public ResponseEntity<List<Optional<Pokemon>>> getAlineacion(@PathVariable int id) { // Método que devuelve la alineación de un entrenador
        Entrenador entrenador = servicioEntrenador.recuperarEntrenador1();
        List<Pokemon> pokemons = entrenador.getPokemons();
        List<Optional<Pokemon>> alineacion = new ArrayList<>();
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getAlineacion() != null) {
                Optional<Pokemon> oPokemon = Optional.of(pokemon);
                alineacion.add(oPokemon);
            }
        }
        return new ResponseEntity<>(alineacion, HttpStatus.OK);
    }

}
