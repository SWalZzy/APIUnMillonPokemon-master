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
import org.antlr.v4.runtime.misc.Array2DHashSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity // Indica que la clase es una entidad
@Table(name = "entrenador") // Indica el nombre de la tabla en la base de datos
@NoArgsConstructor // Genera un constructor vacío
@AllArgsConstructor // Genera un constructor con todos los argumentos
@Data // Genera los getters y setters
@ToString // Genera el método toString
public class Entrenador {
    @Id // Indica que es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que es autoincremental
    private int id;

    @Column(length = 45, nullable = false) // Indica que es una columna en la base de datos
    private String nombre;

    @Column(length = 45, nullable = false)
    private String apellido;

    @Column(length = 255)
    private String icono;

    @Column(nullable = false)
    private float dinero;

    @OneToMany(mappedBy = "entrenador", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Indica que es una relación uno a muchos
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // Indica que es una relación de identidad
    private List<Pokemon> pokemons = new ArrayList<>();

    @OneToMany(mappedBy = "entrenador", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // Indica que es una relación uno a muchos
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // Indica que es una relación de identidad
    private List<Resultado> resultados = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL) // Indica que es una relación uno a uno
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // Indica que es una relación de identidad
    @JoinColumn(name = "usuario_id") // Indica el nombre de la columna en la base de datos
    private Usuario usuario;



}
