package com.ada.proyectofinal.services;

import com.ada.proyectofinal.entities.Entrenador;
import com.ada.proyectofinal.repositories.RepositoryEntrenador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Anotación que indica que la clase es un servicio
public class ServicioEntrenador {
    @Autowired // Anotación que permite la inyección de dependencias
    private RepositoryEntrenador repositoryEntrenador;

    // Método que devuelve una lista de entrenadores
    public List<Entrenador> findAll(){
        return repositoryEntrenador.findAll();
    }

    // Método que devuelve un entrenador por id
    public Entrenador findById(int id){
        return repositoryEntrenador.findById(id);
    }

    // Método que guarda un entrenador
    public void save(Entrenador entrenador){
        repositoryEntrenador.save(entrenador);
    }

    // Método que elimina un entrenador por id
    public void deleteById(int id){
        repositoryEntrenador.deleteById(id);
    }

    // Método que elimina todos los entrenadores
    public void deleteAll(){
        repositoryEntrenador.deleteAll();
    }

    // Método que devuelve un entrenador por nombre
    public Entrenador recuperarEntrenador1(){
        return repositoryEntrenador.findById(1);
    }

    // Método que devuelve un entrenador por nombre
    public List<Entrenador> recuperarBots(){
        return repositoryEntrenador.recuperarBots();
    }

}
