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

@Entity // Indica que la clase es una entidad
@Table(name = "resultado") // Indica el nombre de la tabla en la base de datos
@AllArgsConstructor // Crea un constructor con todos los atributos de la clase
@NoArgsConstructor // Crea un constructor vacío
@Data // Crea getters y setters
@ToString // Crea un toString
public class Resultado {
    @Id // Indica que es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que es autoincremental
    private int id;

    @Column // Indica que es una columna en la base de datos
    private String ganador;
    @Column
    private String perdedor;
    @Column
    private boolean empate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})// Indica que es una relación muchos a uno
    @JoinColumn(name = "entrenador_id")// Indica el nombre de la columna en la base de datos
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")// Indica que es un identificador
    private Entrenador entrenador;
}
