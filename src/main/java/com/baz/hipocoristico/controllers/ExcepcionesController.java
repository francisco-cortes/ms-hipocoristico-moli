package com.baz.moli.controllers;

import com.baz.moli.dtos.HipocoristicoResponseDto;
import com.baz.moli.exceptions.ErrorInternoExepcion;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
        * <b>ExepcionesController</b>
        * @descripcion: Controlador de exepciones y respuestas controladas
        * @autor: Francisco Javier Cortes Torres, Desarrollador
        * @ultimaModificacion: 04/10/22
       */
@RestControllerAdvice
public class ExepcionesController  {
  /**
   * <b>ErrorInternoException</b>
   * @descripcion: Método para personzalizar la salida de una excepción del tipo 500.
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 5/10/22
   */
  @ExceptionHandler(value = {ErrorInternoExepcion.class})
  public HipocoristicoResponseDto errorInterno(ErrorInternoExepcion errorInterno) {
    /*
    construccion del metodo de salida
     */
    return new HipocoristicoResponseDto(errorInterno.getCodigo(),errorInterno.getNombres(),
      errorInterno.getApellidos(), errorInterno.getMensaje(), errorInterno.getDetalles());
  }

}
