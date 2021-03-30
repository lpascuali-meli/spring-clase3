package com.spring.starwars.services;

import com.spring.starwars.repositories.IPersonajeRepository;
import com.spring.starwars.dto.PersonajeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PersonajeService implements IPersonajeService{
    private IPersonajeRepository personajeRepository;

    public PersonajeService(IPersonajeRepository personajeRepository) {
        this.personajeRepository = personajeRepository;
    }

    @Override
    public ResponseEntity getPersonaje(String name) {
        ArrayList<PersonajeDto> personajes = personajeRepository.getPersonajes(name);
        if (personajes.size() == 0) { return new ResponseEntity("No se encontró el personaje", HttpStatus.NOT_FOUND);}
        return new ResponseEntity(personajes, HttpStatus.OK);
    }
}
