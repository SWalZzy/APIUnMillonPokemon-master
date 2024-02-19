package com.ada.proyectofinal.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // Indica que la clase es una entidad
@NoArgsConstructor // Crea un constructor sin argumentos
@AllArgsConstructor // Crea un constructor con todos los argumentos
@Data // Crea los getters, setters y otros métodos
@ToString // Crea un método toString
public class Usuario {
    @Id // Indica que es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Indica que es autoincremental
    private int id;

    @Column // Indica que es una columna
    private String username;

    @Column
    private String password;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL) // Indica que es una relación uno a uno
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // Indica que es un objeto anidado
    private Entrenador entrenador;

}
