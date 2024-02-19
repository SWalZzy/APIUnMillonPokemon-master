package com.ada.proyectofinal.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity // Indica que la clase es una entidad
@Table(name = "pokemon") // Indica el nombre de la tabla en la base de datos
@NoArgsConstructor // Crea un constructor vacío
@AllArgsConstructor // Crea un constructor con todos los atributos de la clase
@Data // Crea getters y setters
@ToString // Crea un toString
public class Pokemon {

    @Id // Indica que es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que es autoincremental
    private int id;

    @Column(length = 45, nullable = false) // Indica que no puede ser nulo
    private String name;

    @Column(length = 45, nullable = false)
    private String type;

    @Column(nullable = false)
    private int level;

    @Column(length = 100, nullable = false)
    private int hp;

    @Column(length = 45, nullable = false)
    private int attack;

    @Column(length = 45, nullable = false)
    private int defence;

    @Column(length = 45, nullable = false)
    private int speed;

    @Column
    private String gender;

    @Column
    private float valor;

    @Column
    private String hiresURL;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})// Indica que es una relación muchos a uno
    @JoinColumn(name = "entrenador_id") // Indica el nombre de la columna en la base de datos
    @JsonIdentityReference(alwaysAsId = true) // Indica que es un identificador
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // Indica que es un identificador
    private Entrenador entrenador;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE}) // Indica que es una relación muchos a uno
    @JoinColumn(name = "mercado_id") // Indica el nombre de la columna en la base de datos
    @JsonIdentityReference(alwaysAsId = true) // Indica que es un identificador
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // Indica que es un identificador
    private Mercado mercado;

    @ManyToOne(cascade = CascadeType.ALL) // Indica que es una relación muchos a uno
    @JoinColumn(name = "alineacion_id") // Indica el nombre de la columna en la base de datos
    @JsonIdentityReference(alwaysAsId = true) // Indica que es un identificador
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")// Indica que es un identificador
    private Alineacion alineacion;
}
