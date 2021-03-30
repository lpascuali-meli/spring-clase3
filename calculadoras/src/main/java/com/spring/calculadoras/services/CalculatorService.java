package com.spring.calculadoras.services;

import com.spring.calculadoras.dto.IngredienteDto;
import com.spring.calculadoras.dto.PlatoDto;
import com.spring.calculadoras.repositories.ICalculatorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CalculatorService implements ICalculatorService{
    private ICalculatorRepository calculatorRepository;

    public CalculatorService(ICalculatorRepository calculatorRepository) {
        this.calculatorRepository = calculatorRepository;
    }

    @Override
    public ResponseEntity obtenerCaloriasPlato(PlatoDto plato) {
        if (!addCaloriesToIngredients(plato)) { return new ResponseEntity("No se encontraron los ingredientes", HttpStatus.BAD_REQUEST); }
        double calories = 0;
        for (IngredienteDto ing: plato.getIngredientes()) {
            calories += ing.getCalories() * ing.getWeight();
        }
        return new ResponseEntity<>(calories, HttpStatus.OK);
    }

    @Override
    public ResponseEntity obtenerCaloriasIngredientes(PlatoDto plato) {
        if (!addCaloriesToIngredients(plato)) { return new ResponseEntity("No se encontraron los ingredientes", HttpStatus.BAD_REQUEST); }
        return new ResponseEntity<>(plato.getIngredientes(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity obtenerMaximaCaloria(PlatoDto plato) {
        if (!addCaloriesToIngredients(plato)) { return new ResponseEntity("No se encontraron los ingredientes", HttpStatus.BAD_REQUEST); }
        IngredienteDto maxIngredient = new IngredienteDto();
        for (IngredienteDto ing: plato.getIngredientes()) {
            if (ing.getCalories() > maxIngredient.getCalories()) {
                maxIngredient = ing;
            }
        }
        return new ResponseEntity<>(maxIngredient, HttpStatus.OK);
    }


    // Función que agrega las calorías a cada ingrediente del plato
    // retorna false si no se encuentran los ingredientes
    private boolean addCaloriesToIngredients(PlatoDto plato) {
        HashMap<String, IngredienteDto> ingredientesEncontrados = calculatorRepository.getAllIngredients(plato.getIngredientes());
        if (ingredientesEncontrados.size() == 0) { return false; }
        for (IngredienteDto ing: plato.getIngredientes()) {
            ing.setCalories(ingredientesEncontrados.get(ing.getName()).getCalories());
        }
        return true;
    }
}
