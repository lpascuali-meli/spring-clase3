package com.spring.calculadoras.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.calculadoras.dto.IngredienteDto;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class CalculatorRepository implements ICalculatorRepository{

    // Consulta todos los ingredientes cuyo nombre coincida con los del arreglo que recibe por parámetro
    @Override
    public HashMap<String, IngredienteDto> getAllIngredients(List<IngredienteDto> ingredientesABuscar) {
        HashMap<String, IngredienteDto> resultado = new HashMap<String, IngredienteDto>();
        List<IngredienteDto> ingredientes = null;
        ingredientes = loadDataBase();
        if (ingredientes != null){
            for (IngredienteDto ing: ingredientesABuscar) {
                Optional<IngredienteDto> item = ingredientes.stream()
                        .filter(ingredientDto -> ingredientDto.getName().equals(ing.getName()))
                        .findFirst();
                item.ifPresent(ingredienteDto -> resultado.put(ing.getName(), ingredienteDto));
            }
        }

        return resultado;
    }


    // Carga el archivo de ingredientes
    private List<IngredienteDto> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:food.json");
        }catch (Exception e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<IngredienteDto>> typeRef = new TypeReference<List<IngredienteDto>>() {};
        List<IngredienteDto> ingredients = null;

        try {
            ingredients = objectMapper.readValue(file, typeRef);

        }catch (Exception e){
            e.printStackTrace();
        }

        return ingredients;

    }
}
