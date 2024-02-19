package com.ada.proyectofinal.repositories;

import com.ada.proyectofinal.entities.Alineacion;
import com.ada.proyectofinal.entities.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //Indica que es un repositorio
public interface RepositoryAlineacion extends JpaRepository<Alineacion, Integer>{
    @Query(value = "SELECT * FROM alineacion WHERE id = :id", nativeQuery = true) //Consulta nativa
    Alineacion findById(int id);//Busca una alineación por id
}
