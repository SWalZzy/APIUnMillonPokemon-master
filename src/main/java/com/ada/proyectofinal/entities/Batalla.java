package com.ada.proyectofinal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // Indica que la clase es una entidad
@Table(name = "batalla") // Indica el nombre de la tabla en la base de datosº
@AllArgsConstructor // Genera un constructor con todos los argumentos
@NoArgsConstructor // Genera un constructor vacío
@Data // Genera los getters y setters
@ToString // Genera el método toString
public class Batalla {
    @Id // Indica que es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que es autoincremental
    private int id;
}
