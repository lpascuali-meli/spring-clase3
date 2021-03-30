package com.spring.calculadoras.repositories;

import com.spring.calculadoras.dto.IngredienteDto;

import java.util.HashMap;
import java.util.List;

public interface ICalculatorRepository {
    public HashMap<String, IngredienteDto> getAllIngredients(List<IngredienteDto> ingredientesABuscar);
}
