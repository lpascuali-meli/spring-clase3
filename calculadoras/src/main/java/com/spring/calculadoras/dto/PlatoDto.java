package com.spring.calculadoras.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatoDto {
    private String nombre;
    private ArrayList<IngredienteDto> ingredientes;
}
