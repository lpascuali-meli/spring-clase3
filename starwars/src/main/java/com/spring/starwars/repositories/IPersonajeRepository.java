package com.spring.starwars.repositories;

import com.spring.starwars.dto.PersonajeDto;

import java.util.ArrayList;


public interface IPersonajeRepository {
    public ArrayList<PersonajeDto> getPersonajes(String name);
}
