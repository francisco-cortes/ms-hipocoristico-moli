package com.baz.hipocoristico.controllers;

import com.baz.hipocoristico.dtos.HipocoristicoResponseDto;
import com.baz.hipocoristico.exceptions.ErrorInternoExepcion;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <b>ExcepcionesController</b>
 * @descripcion: Controlador de exepciones y respuestas controladas
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 13/10/22
 */
@RestControllerAdvice
public class ExcepcionesController {
  /**
   * <b>ErrorInternoException</b>
   * @descripcion: Método para personzalizar la salida de una excepción del tipo 500.
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @param: errorInterno datos de la exepcion
   * @ultimaModificacion: 13/10/22
   */
  @ExceptionHandler(value = {ErrorInternoExepcion.class})
  public HipocoristicoResponseDto errorInterno(ErrorInternoExepcion errorInterno) {
    /*
    Construccion del metodo de salida
     */
    return new HipocoristicoResponseDto(
      errorInterno.getCodigo(),
      errorInterno.getNombres(),
      errorInterno.getApellidos(),
      errorInterno.getMensaje(),
      errorInterno.getDetalles());
  }

}
