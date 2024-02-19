package com.ada.proyectofinal.services;

import ch.qos.logback.core.BasicStatusManager;
import com.ada.proyectofinal.Parser.Parser;
import com.ada.proyectofinal.entities.*;
import com.ada.proyectofinal.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service // Anotación que indica que la clase es un servicio
public class DataInicializer {

    @Autowired // Anotación que permite la inyección de dependencias
    private RepositoryPokemon repositoryPokemon;

    @Autowired
    private RepositoryEntrenador repositoryEntrenador;

    @Autowired
    private RepositoryMercado repositoryMercado;

    @Autowired
    private RepositoryAlineacion repositoryAlineacion;

    @Autowired
    private Parser parser;

    public void GenerarEntrenadores(Entrenador entrenadorRecibido) { // Método que genera los entrenadores
        List<Entrenador> entrenadores = repositoryEntrenador.findAll();
        if (entrenadores.isEmpty()) {
            Entrenador entrenador = new Entrenador();
            entrenador.setNombre(entrenadorRecibido.getNombre());
            entrenador.setApellido(entrenadorRecibido.getApellido());
            entrenador.setDinero(entrenadorRecibido.getDinero());
            if (entrenadorRecibido.getIcono() == null) {
                entrenador.setIcono(null);
            }
            repositoryEntrenador.save(entrenador);
            for (Pokemon pokemon : entrenadorRecibido.getPokemons()) {
                pokemon.setEntrenador(entrenador);
                repositoryPokemon.save(pokemon);
            }
            if (!entrenadorRecibido.getResultados().isEmpty()) {
                for (Resultado resultado : entrenadorRecibido.getResultados()) {
                    resultado.setEntrenador(entrenador);
                }
            }

            Entrenador entrenador1 = new Entrenador();
            entrenador1.setNombre("Ash");
            entrenador1.setApellido("Ketchum");
            entrenador1.setDinero(150000);
            entrenador1.setIcono(null);
            repositoryEntrenador.save(entrenador1);

            Entrenador entrenador2 = new Entrenador();
            entrenador2.setNombre("Misty");
            entrenador2.setApellido("Waterflower");
            entrenador2.setDinero(150000);
            entrenador2.setIcono(null);
            repositoryEntrenador.save(entrenador2);

            Entrenador entrenador3 = new Entrenador();
            entrenador3.setNombre("Brock");
            entrenador3.setApellido("Takeshi");
            entrenador3.setDinero(150000);
            entrenador3.setIcono(null);
            repositoryEntrenador.save(entrenador3);

            Entrenador entrenador4 = new Entrenador();
            entrenador4.setNombre("Gary");
            entrenador4.setApellido("Oak");
            entrenador4.setDinero(150000);
            entrenador4.setIcono(null);
            repositoryEntrenador.save(entrenador4);
        }
    }

    public void generarPokemons() { // Método que genera los pokemons
        List<Pokemon> pokemons = repositoryPokemon.findAll();
        if (pokemons.isEmpty()) {
            pokemons = new ArrayList<>();
            if (parser.parse()) {
                pokemons = parser.getPokemons();
            }
        }
        repositoryPokemon.saveAll(pokemons);
    }

    public void asignarPokemons() { // Método que asigna los pokemons a los entrenadores
        List<Pokemon> pokemons = repositoryPokemon.comprobarPokemonsSinEntrenadorNiMercado();
        if (!pokemons.isEmpty()) {
            if (!repositoryPokemon.comprobarPokemonsSinEntrenadorNiMercado().isEmpty()) {
                List<Entrenador> entrenadores = repositoryEntrenador.findAll();
                int limit = 20;
                for (Entrenador entrenador : entrenadores) {
                    List<Pokemon> pokemonsParaEntrenador = repositoryPokemon.recuperarPokemons(limit);
                    asignar20Pokemons(pokemonsParaEntrenador, entrenador.getId());
                }
            }
        }
    }

    private void asignar20Pokemons(List<Pokemon> pokemons, int id_entrenador) { // Método que asigna 20 pokemons a cada entrenador
        Entrenador entrenador = repositoryEntrenador.findById(id_entrenador);
        for (Pokemon pokemon : pokemons) {
            pokemon.setEntrenador(entrenador);
        }
        entrenador.setPokemons(pokemons);
        repositoryEntrenador.save(entrenador);
    }

    public void prepararMercado() { // Método que prepara el mercado
        List<Mercado> mercados = repositoryMercado.findAll();
        if (mercados.isEmpty()) {
            Mercado mercado = new Mercado();
            mercado.setFecha(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
            List<Pokemon> pokemons = repositoryPokemon.comprobarPokemonsSinEntrenadorNiMercado();
            for (Pokemon pokemon : pokemons) {
                pokemon.setMercado(mercado);
            }
            mercado.setPokemons(pokemons);
            repositoryMercado.save(mercado);
        }
    }

    public void generarAlineacion() { // Método que genera las alineaciones
        List<Alineacion> alineaciones = repositoryAlineacion.findAll();
        if (alineaciones.isEmpty()) {
            for (int i = 1; i < 5; i++) {
                Alineacion alineacion = new Alineacion();
                alineacion.setZona("Zona" + i);
                repositoryAlineacion.save(alineacion);
            }
        }
    }

    public void asignarAlineaciones() { // Método que asigna las alineaciones a los pokemons
        List<Entrenador> entrenadores = repositoryEntrenador.recuperarBots();
        List<Pokemon> pokemonsAlineados = new ArrayList<>();
        List<Alineacion> alineaciones;
        List<Pokemon> pokemons;
        List<Integer> pokemons_id = new ArrayList<>();
        int idEntrenador = 0;
        for (Entrenador entrenador : entrenadores) {
            idEntrenador = entrenador.getId();
            pokemons = entrenador.getPokemons();
            Collections.shuffle(pokemons);
            alineaciones = repositoryAlineacion.findAll();
            List<Pokemon> zona1 = new ArrayList<>();
            List<Pokemon> zona2 = new ArrayList<>();
            List<Pokemon> zona3 = new ArrayList<>();
            List<Pokemon> zona4 = new ArrayList<>();
            for (Alineacion alineacione : alineaciones) {
                for (Pokemon pokemon : pokemons) {
                    if (!pokemons_id.contains(pokemon.getId())) {
                        switch (alineacione.getId()) {
                            case 1:
                                if (zona1.size() < 2) {
                                    zona1.add(pokemon);
                                    pokemons_id.add(pokemon.getId());
                                    pokemon.setAlineacion(alineacione);
                                }
                                break;
                            case 2:
                                if (zona2.size() < 3) {
                                    zona2.add(pokemon);
                                    pokemons_id.add(pokemon.getId());
                                    pokemon.setAlineacion(alineacione);
                                }
                                break;
                            case 3:
                                if (zona3.size() < 4) {
                                    zona3.add(pokemon);
                                    pokemons_id.add(pokemon.getId());
                                    pokemon.setAlineacion(alineacione);
                                }
                                break;
                            case 4:
                                if (zona4.size() < 2) {
                                    zona4.add(pokemon);
                                    pokemons_id.add(pokemon.getId());
                                    pokemon.setAlineacion(alineacione);
                                }
                                break;
                        }
                    }
                }
                pokemonsAlineados.addAll(zona1);
                pokemonsAlineados.addAll(zona2);
                pokemonsAlineados.addAll(zona3);
                pokemonsAlineados.addAll(zona4);
                repositoryPokemon.saveAll(pokemonsAlineados);
                repositoryEntrenador.save(repositoryEntrenador.findById(idEntrenador));
            }
        }
    }
}
