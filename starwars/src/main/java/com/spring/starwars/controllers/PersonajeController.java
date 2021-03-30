package com.spring.starwars.controllers;

import com.spring.starwars.services.IPersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personaje")
public class PersonajeController {
    @Autowired
    private IPersonajeService personajeService;

    // Calcula las calorias del plato
    @GetMapping("/{name}")
    public ResponseEntity getPersonaje(@PathVariable String name) {
        return personajeService.getPersonaje(name);
    }
}
