package com.ada.proyectofinal.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity // Indica que la clase es una entidad
@Table(name = "mercado") // Indica el nombre de la tabla en la base de datos
@AllArgsConstructor // Crea un constructor con todos los atributos de la clase
@NoArgsConstructor // Crea un constructor vacío
@Data // Crea getters y setters
@ToString // Crea un toString
public class Mercado {

    @Id // Indica que es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que es autoincremental
    private int id;

    @Column(nullable = false) // Indica que no puede ser nulo
    private String fecha;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER) // Indica que es una relación uno a muchos
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // Indica que es un identificador
    private List<Pokemon> pokemons = new ArrayList<>();

}
