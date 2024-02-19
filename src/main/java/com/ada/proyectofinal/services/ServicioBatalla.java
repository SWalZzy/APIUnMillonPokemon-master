package com.ada.proyectofinal.services;

import com.ada.proyectofinal.entities.Batalla;
import com.ada.proyectofinal.entities.Entrenador;
import com.ada.proyectofinal.entities.Resultado;
import com.ada.proyectofinal.repositories.RepositoryBatalla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Anotación que indica que la clase es un servicio
public class ServicioBatalla {

    @Autowired // Anotación que permite la inyección de dependencias
    RepositoryBatalla repositoryBatalla;


    // Método que devuelve una lista de batallas
    public List<Batalla> findAll(){
        return repositoryBatalla.findAll();
    }

    // Método que devuelve una batalla por id
    public Batalla findById(int id){
        return repositoryBatalla.findById(id);
    }

    // Método que guarda una batalla
    public void save(Batalla batalla){
        repositoryBatalla.save(batalla);
    }

    // Método que elimina una batalla por id
    public void deleteById(int id){
        repositoryBatalla.deleteById(id);
    }

    // Método que elimina todas las batallas
    public void deleteAll(){
        repositoryBatalla.deleteAll();
    }
}
