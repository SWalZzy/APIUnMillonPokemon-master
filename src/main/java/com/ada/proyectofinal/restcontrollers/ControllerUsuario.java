package com.ada.proyectofinal.restcontrollers;

import com.ada.proyectofinal.entities.Usuario;
import com.ada.proyectofinal.services.ServicioUsuario;
import com.ada.proyectofinal.services.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Anotación que indica que la clase es un controlador de una API REST
@RequestMapping("/user") // Anotación que indica la URL base para todas las peticiones
public class ControllerUsuario {
    @Autowired // Inyección de dependencias
    ServicioUsuario servicioUsuario;

    @Autowired
    TokenGenerator tokenGenerator;

    @PostMapping("/login") // Anotación que indica que el método maneja las peticiones POST
    public ResponseEntity<String> checkLogIn(@RequestBody Usuario usuario){ // Método que verifica el login
        if(servicioUsuario.checkUser(usuario.getUsername(),usuario.getPassword())){
            return new ResponseEntity<>(tokenGenerator.generateToken(usuario.getUsername()), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/signin") // Anotación que indica que el método maneja las peticiones POST
    public ResponseEntity<String> addUser(@RequestBody Usuario usuario){ // Método que agrega un usuario
        if(servicioUsuario.save(usuario)) {
            return new ResponseEntity<>(tokenGenerator.generateToken(usuario.getUsername()),HttpStatus.OK);
        }else {
            return  new ResponseEntity<>("",HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
