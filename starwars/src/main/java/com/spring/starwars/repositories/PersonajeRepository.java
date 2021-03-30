package com.spring.starwars.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.starwars.dto.PersonajeDto;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class PersonajeRepository implements IPersonajeRepository{
    @Override
    public ArrayList<PersonajeDto> getPersonajes(String name) {
        List<PersonajeDto> personajes = null;
        personajes = loadDataBase();
        ArrayList<PersonajeDto> personajesEncontrados = new ArrayList<PersonajeDto>();
        personajes.forEach((PersonajeDto personaje) -> {
            String personajeName = personaje.getName().toLowerCase();
            if (personajeName.contains(name.toLowerCase())) { personajesEncontrados.add(personaje); }
        });
        return personajesEncontrados;
    }

    // Carga el archivo de ingredientes
    private List<PersonajeDto> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:starwars.json");
        }catch (Exception e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PersonajeDto>> typeRef = new TypeReference<List<PersonajeDto>>() {};
        List<PersonajeDto> personajes = null;

        try {
            personajes = objectMapper.readValue(file, typeRef);

        }catch (Exception e){
            e.printStackTrace();
        }

        return personajes;

    }
}
