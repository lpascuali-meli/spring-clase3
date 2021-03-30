package com.spring.calculadoras.controllers;

import com.spring.calculadoras.dto.PlatoDto;
import com.spring.calculadoras.services.ICalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    @Autowired
    private ICalculatorService calculatorService;

    // Calcula las calorias del plato
    @PostMapping("/calories")
    public ResponseEntity getTotalCalories(@RequestBody PlatoDto plato) {
        return calculatorService.obtenerCaloriasPlato(plato);
    }

    // Devuelve una lista de ingredientes con las calorias
    @PostMapping("/calories-ingredients")
    public ResponseEntity getCaloriasIngredientes(@RequestBody PlatoDto plato) {
        return calculatorService.obtenerCaloriasIngredientes(plato);
    }

    // Devuelve el ingrediente con mayor cantidad de calorias
    @PostMapping("/max-calories")
    public ResponseEntity getMaxCalories(@RequestBody PlatoDto plato) {
        return calculatorService.obtenerMaximaCaloria(plato);
    }
}
