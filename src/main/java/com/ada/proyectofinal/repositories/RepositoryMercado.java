package com.ada.proyectofinal.repositories;

import com.ada.proyectofinal.entities.Mercado;
import com.ada.proyectofinal.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //Le decimos a Spring que esta clase se encarga de la persistencia
public interface RepositoryMercado extends JpaRepository<Mercado, Integer> { //Le decimos a Spring que esta clase se encarga de la persistencia

    @Query(value = "SELECT * FROM mercado WHERE id = :id", nativeQuery = true) //Hacemos una consulta SQL
    Mercado findById(int id); //Metodo que devuelve un mercado por su id
}
