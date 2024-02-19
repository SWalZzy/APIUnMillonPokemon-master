package com.ada.proyectofinal.services;

import com.ada.proyectofinal.entities.Resultado;
import com.ada.proyectofinal.repositories.RepositoryResultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Anotación que indica que la clase es un servicio
public class ServicioResultado {

    @Autowired // Inyección de dependencias
    private RepositoryResultado repositoryResultado;

    // Métodos CRUD
    public void save(Resultado resultado){
        repositoryResultado.save(resultado);
    }

    public void deleteById(int id){
        repositoryResultado.deleteById(id);
    }

    public void deleteAll(){
        repositoryResultado.deleteAll();
    }
}
