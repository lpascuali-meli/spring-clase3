package com.spring.calculadoras.services;

import com.spring.calculadoras.dto.PlatoDto;
import org.springframework.http.ResponseEntity;

public interface ICalculatorService {
    public ResponseEntity obtenerCaloriasPlato(PlatoDto plato);
    public ResponseEntity obtenerCaloriasIngredientes(PlatoDto plato);
    public ResponseEntity obtenerMaximaCaloria(PlatoDto plato);
}
