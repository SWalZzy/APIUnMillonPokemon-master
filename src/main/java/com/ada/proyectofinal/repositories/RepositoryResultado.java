package com.ada.proyectofinal.repositories;

import com.ada.proyectofinal.entities.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//Le decimos a Spring que esta clase se encarga de la persistencia
public interface RepositoryResultado extends JpaRepository<Resultado, Integer> {

}
