package com.ada.proyectofinal.repositories;

import com.ada.proyectofinal.entities.Batalla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository // Le decimos a Spring que esta clase se encarga de interactuar con la base de datos
public interface RepositoryBatalla extends JpaRepository<Batalla, Integer> {
    @Query(value = "SELECT * FROM batalla where id = :id", nativeQuery = true) // Hacemos una consulta SQL para buscar una batalla por su id
    Batalla findById(int id); // Creamos un método que recibe un id y devuelve una batalla
}
