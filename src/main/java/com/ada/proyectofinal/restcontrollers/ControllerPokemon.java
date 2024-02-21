package com.ada.proyectofinal.restcontrollers;

import com.ada.proyectofinal.entities.Entrenador;
import com.ada.proyectofinal.entities.Pokemon;
import com.ada.proyectofinal.services.ServicioPokemon;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Anotación que indica que la clase es un controlador de una API REST
@RequestMapping("/pokemon") // Anotación que indica la URL base para todas las peticiones
public class ControllerPokemon {
    @Autowired // Inyección de dependencias
    private ServicioPokemon servicioPokemon;

    @GetMapping("/pokemons") // Anotación que indica que el método maneja las peticiones GET
    public List<Pokemon> showAllPokemons(){
        return servicioPokemon.findAll();
    } // Método que devuelve todos los pokemons

    @GetMapping("/entrenador/{id}") // Anotación que indica que el método maneja las peticiones GET
    public List<Pokemon> showTrainerPokemons(@PathVariable("id") int id){ // Método que devuelve los pokemons de un entrenador
        return servicioPokemon.getPokemonsByEntrenadorId(id); // Método que devuelve los pokemons de un entrenador
    }
    @GetMapping("/pokemon/{id}") // Anotación que indica que el método maneja las peticiones GET
    public Pokemon showPokemon(@PathVariable("id") int id){
        return servicioPokemon.findById(id);
    } // Método que devuelve un pokemon
}
