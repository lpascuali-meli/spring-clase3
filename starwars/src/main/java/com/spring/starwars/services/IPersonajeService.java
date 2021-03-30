package com.spring.starwars.services;
import org.springframework.http.ResponseEntity;

public interface IPersonajeService {
    public ResponseEntity getPersonaje(String name);
}
