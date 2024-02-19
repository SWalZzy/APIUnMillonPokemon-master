package com.ada.proyectofinal.services;

import com.ada.proyectofinal.entities.Mercado;
import com.ada.proyectofinal.entities.Pokemon;
import com.ada.proyectofinal.repositories.RepositoryMercado;
import com.ada.proyectofinal.repositories.RepositoryPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Anotación que indica que la clase es un servicio
public class ServicioMercado {
    @Autowired // Anotación que permite la inyección de dependencias
    private RepositoryMercado repositoryMercado;
    @Autowired
    private RepositoryPokemon repositoryPokemon;


    // Método que devuelve una lista de mercados
    public List<Mercado> findAll(){
        return repositoryMercado.findAll();
    }

    // Método que devuelve un mercado por id
    public Mercado findById(int id){
        return repositoryMercado.findById(id);
    }

    // Método que guarda un mercado
    public void saveAll(List<Mercado> mercados){
        repositoryMercado.saveAll(mercados);
    }

    // Método que guarda un mercado
    public void save(Mercado mercado){
        repositoryMercado.save(mercado);
    }

    // Método que elimina un mercado por id
    public void deleteById(int id){
        repositoryMercado.deleteById(id);
    }

    // Método que elimina todos los mercados
    public void deleteAll(){
        repositoryMercado.deleteAll();
    }

    // Método que devuelve un mercado por nombre
    public List<Pokemon> ListarPokemonsDelMercado(){
       return repositoryPokemon.mostrarPokemonsEnVenta();
    }

    // Método que devuelve un mercado por nombre
    public List<Pokemon> comprarPokemon(){
        return repositoryPokemon.mostrarPokemonsEnVenta();
    }


}
