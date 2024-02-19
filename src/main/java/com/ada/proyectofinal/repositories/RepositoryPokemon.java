package com.ada.proyectofinal.repositories;

import com.ada.proyectofinal.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository //Le decimos a Spring que esta clase se encarga de la persistencia
public interface RepositoryPokemon extends JpaRepository<Pokemon, Integer>{//Le decimos a Spring que esta clase se encarga de la persistencia
    @Query(value = "SELECT * FROM pokemon WHERE entrenador_id IS NULL Limit :limit", nativeQuery = true)//Hacemos una consulta SQL
    List<Pokemon> recuperarPokemons(int limit);//Metodo que devuelve una lista de pokemons sin entrenador

    @Query(value = "SELECT * FROM pokemon where id = :id", nativeQuery = true)
    Pokemon findById(int id);//Metodo que devuelve un pokemon por su id

    @Query(value = "SELECT * FROM pokemon WHERE entrenador_id = :id",nativeQuery = true)
    List<Pokemon> getPokemonsByEntrenadorId(int id);//Metodo que devuelve una lista de pokemons por el id del entrenador

    @Query(value = "SELECT * FROM pokemon WHERE entrenador_id IS NULL AND mercado_id IS NULL", nativeQuery = true)
    List<Pokemon> comprobarPokemonsSinEntrenadorNiMercado();//Metodo que devuelve una lista de pokemons sin entrenador ni mercado

    @Query(value = "SELECT * FROM pokemon where entrenador_id is null AND mercado_id = 1", nativeQuery = true)
    List<Pokemon> mostrarPokemonsEnVenta();//Metodo que devuelve una lista de pokemons en venta

}
