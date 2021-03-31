package com.spring.calculadoras.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredienteDto {
    private String name;
    private double weight;
    private double calories;

    @Override
    public String toString() {
        return "IngredienteDto{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", calories=" + calories +
                '}';
    }
}
