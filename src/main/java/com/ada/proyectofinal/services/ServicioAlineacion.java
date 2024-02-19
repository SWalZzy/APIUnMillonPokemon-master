package com.ada.proyectofinal.services;

import com.ada.proyectofinal.entities.Alineacion;
import com.ada.proyectofinal.entities.Pokemon;
import com.ada.proyectofinal.repositories.RepositoryAlineacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service // Anotación que indica que la clase es un servicio
public class ServicioAlineacion {
    @Autowired // Anotación que permite la inyección de dependencias
    RepositoryAlineacion repositoryAlineacion;

    // Método que devuelve una lista de alineaciones
    public List<Alineacion> findAll(){
        return repositoryAlineacion.findAll();
    }
    // Método que devuelve una alineación por id
    public Alineacion findById(int id){
        return repositoryAlineacion.findById(id);
    }
    // Método que devuelve una lista de alineaciones por id de entrenador
    public void save(Alineacion alineacion){
        repositoryAlineacion.save(alineacion);
    }
    // Método que guarda una alineación
    public void deleteById(int id){
        repositoryAlineacion.deleteById(id);
    }
    // Método que elimina una alineación por id
    public void deleteAll(){
        repositoryAlineacion.deleteAll();
    }
    // Método que elimina todas las alineaciones
}
