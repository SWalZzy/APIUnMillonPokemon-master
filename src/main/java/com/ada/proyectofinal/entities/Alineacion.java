package com.ada.proyectofinal.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity // Indica que la clase es una entidad
@Data // Genera los getters y setters
@NoArgsConstructor // Genera un constructor vacío
@AllArgsConstructor // Genera un constructor con todos los argumentos
@Table(name = "alineacion")// Indica el nombre de la tabla en la base de datos
public class Alineacion {

    @Id // Indica que es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Indica que es autoincremental
    private int id;

    @Column // Indica que es una columna en la base de datos
    private String zona;

    @OneToMany(mappedBy = "alineacion", cascade = CascadeType.ALL)// Indica que es una relación uno a muchos
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")// Indica que es una relación de identidad
    private List<Pokemon> pokemons;



}
