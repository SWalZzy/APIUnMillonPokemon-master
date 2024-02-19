package com.ada.proyectofinal.repositories;

import com.ada.proyectofinal.entities.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Le decimos a Spring que esta clase es un bean y que debe ser gestionado por el contenedor de Spring
public interface RepositoryEntrenador extends JpaRepository<Entrenador, Integer> { // JpaRepository<Entrenador, Integer> es una interfaz que nos provee Spring Data JPA para realizar operaciones CRUD sobre la entidad Entrenador

    @Query(value = "SELECT * FROM entrenador WHERE id = :id", nativeQuery = true) // Con esta anotación le decimos a Spring que ejecute la consulta SQL que le pasamos como parámetro
    Entrenador findById(int id); // Método que nos permite buscar un entrenador por su id

    @Query(value = "SELECT * FROM entrenador where id != 1", nativeQuery = true) // Con esta anotación le decimos a Spring que ejecute la consulta SQL que le pasamos como parámetro
    List<Entrenador> recuperarBots(); // Método que nos permite recuperar todos los entrenadores que no sean el bot

}
