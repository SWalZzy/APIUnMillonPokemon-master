package com.ada.proyectofinal.Parser;

import com.ada.proyectofinal.entities.Pokemon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service //Indica que es un servicio
public class Parser {
    @Autowired //Inyección de dependencias
    private ResourceLoader resourceLoader;
    private List<Pokemon> pokemons;

    public Parser() {
    }

    //TODO:Balance level with stats
    public boolean parse() { //Parsea el archivo pokedex.json
        InputStream file;
        try {
            file = resourceLoader.getResource("classpath:pokedex.json").getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean parsed = false;
        String json = null;
        int size;
        pokemons = new ArrayList<>();

        try {
            size = file.available();
            byte[] buffer = new byte[size];
            file.read(buffer);
            file.close();
            json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                int id = object.getInt("id");
                JSONObject nameObject = object.getJSONObject("name");
                String name = nameObject.getString("english");
                JSONArray typeArray = object.getJSONArray("type");
                String type = typeArray.getString(0);
                System.out.println(object.getInt("id"));
                JSONObject baseObject = object.getJSONObject("base");
                int hp = baseObject.getInt("HP");
                int attack = baseObject.getInt("Attack");
                int defense = baseObject.getInt("Defense");
                int speed = baseObject.getInt("Speed");
                String gender = object.getJSONObject("profile").getString("gender");
                String hiresUrl = object.getJSONObject("image").getString("hires");
                int level = new Random().nextInt(4)+1;
                pokemons.add(new Pokemon(id,name,type,level,hp,attack,defense,speed,gender,level*1000f,hiresUrl,null,null,null));
                parsed = true;
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
        return parsed;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }//Devuelve la lista de pokemons
}
