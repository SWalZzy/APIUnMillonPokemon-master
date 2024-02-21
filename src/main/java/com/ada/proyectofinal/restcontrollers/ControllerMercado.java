package com.ada.proyectofinal.restcontrollers;

import com.ada.proyectofinal.Parser.Parser;
import com.ada.proyectofinal.entities.Entrenador;
import com.ada.proyectofinal.entities.Mercado;
import com.ada.proyectofinal.entities.Pokemon;
import com.ada.proyectofinal.services.ServicioEntrenador;
import com.ada.proyectofinal.services.ServicioMercado;
import com.ada.proyectofinal.services.ServicioPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController //Anotación que indica que la clase es un controlador de una API REST
public class ControllerMercado {
    @Autowired //Inyección de dependencias
    private ServicioMercado servicioMercado;

    @Autowired
    private ServicioPokemon servicioPokemon;

    @Autowired
    private ServicioEntrenador servicioEntrenador;


    @GetMapping("/mostrarMercado") //Anotación que indica que el método maneja las peticiones GET
    public ResponseEntity<List<Pokemon>> mostrarMercado(){ //Método que devuelve la lista de pokemons del mercado
        List<Pokemon> pokemons = servicioMercado.ListarPokemonsDelMercado();
        return new ResponseEntity<>(pokemons, HttpStatus.OK);
    }

    @PostMapping("/comprarPokemon/{idPokemon}") //Anotación que indica que el método maneja las peticiones POST
    //Método que realiza la compra de un pokemon
    public ResponseEntity<String> comprarPokemon(@PathVariable("idPokemon") int idPokemon, @RequestBody Entrenador entrenador){
        List<Pokemon> pokemons = servicioMercado.comprarPokemon();
        Pokemon pokemonComprar = new Pokemon();
        for(Pokemon pokemon : pokemons){
            if(pokemon.getId()==idPokemon){
               pokemonComprar = pokemon;
               break;
            }
        }
        if (entrenador.getDinero()<=pokemonComprar.getValor()||entrenador.getPokemons().size()>=20){
            return new ResponseEntity<>("No se pudo comprar el pokemon",HttpStatus.NOT_ACCEPTABLE);
        }else{
            entrenador.setDinero(entrenador.getDinero()-pokemonComprar.getValor());
            pokemonComprar.setMercado(null);
            pokemonComprar.setEntrenador(entrenador);
            servicioPokemon.save(pokemonComprar);
            List<Pokemon> pokemonsEntrenador = entrenador.getPokemons();
            pokemonsEntrenador.add(pokemonComprar);
            entrenador.setPokemons(pokemonsEntrenador);
            servicioEntrenador.save(entrenador);

            return new ResponseEntity<>("Se ha hecho la transaccion correctamente",HttpStatus.OK);
        }
    }
    @PostMapping("/venderPokemon/{idPokemon}") //Anotación que indica que el método maneja las peticiones POST
    //Método que realiza la venta de un pokemon
    public ResponseEntity<String> venderPokemon(@RequestBody Entrenador entrenador, @PathVariable("idPokemon") int idPokemon){
        Pokemon pokemonVenta = null;
        for(Pokemon pokemon : entrenador.getPokemons()){
            if(pokemon.getId()==idPokemon){
                pokemon.setEntrenador(null);
                pokemonVenta = pokemon;
                break;
            }
        }
        entrenador.setDinero(entrenador.getDinero()+pokemonVenta.getValor());
        Mercado mercado = servicioMercado.findById(1);
        pokemonVenta.setMercado(mercado);
        servicioPokemon.save(pokemonVenta);
        servicioEntrenador.save(entrenador);
        return new ResponseEntity<>("Se ha vendido de manera exitosa",HttpStatus.OK);
    }

    @PostMapping("/comprarVida/{id}") // Anotación que indica que el método maneja las peticiones POST
    public void comprarVida(@PathVariable("id") int idPokemon){ // Método que realiza la compra de vida
        Pokemon pokemon = servicioPokemon.findById(idPokemon);
        Entrenador entrenador = pokemon.getEntrenador();
        if(pokemon.getHp() < 100 && entrenador.getDinero() >= 30000f){
            pokemon.setHp(pokemon.getHp() + 5);
            entrenador.setDinero(entrenador.getDinero() - 30000f);
            servicioPokemon.save(pokemon);
        }else{
            System.out.println("No Puedes comprar vida");
        }
    }
}
