package com.spring.calculadoras.services;

import com.spring.calculadoras.ExceptionHandler.ErrorDto;
import com.spring.calculadoras.ExceptionHandler.IngredientNotFoundException;
import com.spring.calculadoras.dto.IngredienteDto;
import com.spring.calculadoras.dto.PlatoDto;
import com.spring.calculadoras.repositories.ICalculatorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Service
public class CalculatorService implements ICalculatorService{
    private ICalculatorRepository calculatorRepository;

    public CalculatorService(ICalculatorRepository calculatorRepository) {
        this.calculatorRepository = calculatorRepository;
    }

    @Override
    public ResponseEntity obtenerCaloriasPlato(PlatoDto plato) {
        try {
            addCaloriesToIngredients(plato);
        } catch (IngredientNotFoundException e) {
            ErrorDto error = new ErrorDto();
            error.setName("Ingrediente no encontrado.");
            error.setDescription(e.getMessage());
            return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }

        double calories = 0;
        for (IngredienteDto ing: plato.getIngredientes()) {
            calories += ing.getCalories() * ing.getWeight();
        }
        return new ResponseEntity<>(calories, HttpStatus.OK);
    }

    @Override
    public ResponseEntity obtenerCaloriasIngredientes(PlatoDto plato) {
        try {
            addCaloriesToIngredients(plato);
        } catch (IngredientNotFoundException e) {
            ErrorDto error = new ErrorDto();
            error.setName("Ingrediente no encontrado.");
            error.setName(e.getMessage());
            return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(plato.getIngredientes(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity obtenerMaximaCaloria(PlatoDto plato) {
        try {
            addCaloriesToIngredients(plato);
        } catch (IngredientNotFoundException e) {
            ErrorDto error = new ErrorDto();
            error.setName("Ingrediente no encontrado.");
            error.setName(e.getMessage());
            return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }
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
    private boolean addCaloriesToIngredients(PlatoDto plato) throws IngredientNotFoundException {
        HashMap<String, IngredienteDto> ingredientesEncontrados = calculatorRepository.getAllIngredients(plato.getIngredientes());
        int diferencia = plato.getIngredientes().size() - ingredientesEncontrados.size();
        if (diferencia != 0) {
            ArrayList<IngredienteDto> ingredientesNoEncontrados = new ArrayList<>();
            for (IngredienteDto i: plato.getIngredientes()) {
                if (!ingredientesEncontrados.containsKey(i.getName())) {
                    ingredientesNoEncontrados.add(i);
                }
            }
            throw new IngredientNotFoundException(Arrays.toString(ingredientesNoEncontrados.toArray()));
        }
        for (IngredienteDto ing: plato.getIngredientes()) {
            ing.setCalories(ingredientesEncontrados.get(ing.getName()).getCalories());
        }
        return true;
    }
}
