package com.elektra.hipocoristico.controladores;

import com.baz.excepciones.*;
import com.elektra.hipocoristico.dtos.DtoHipocoristicoResponse;
import com.elektra.hipocoristico.excepcion.ErrorInternoExepcion;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <b>ControladorExcepciones</b>
 * @descripcion: Controlador de exepciones y respuestas controladas
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 13/10/22
 */
@RestControllerAdvice
public class ControladorExcepciones {

  /**
   * <b>badRequestException</b>
   * @descripcion: Método para personzalizar la salida del tipo 400.
   * @autor: Francisco Javier Cortes Torres.
   * @param: peticionincorrecta Datos de la excepción.
   * @ultimaModificacion: 17/10/2022
   */
  @ExceptionHandler(value = {BadRequestException.class})
  public DtoExcepcion badRequestException(BadRequestException peticionIncorrecta){
    return new DtoExcepcion(peticionIncorrecta.getCodigo(),
      peticionIncorrecta.getMensaje(),
      peticionIncorrecta.getFolio(),
      peticionIncorrecta.getDetalles());
  }

  /**
   * <b>UnauthorizedException</b>
   * @descripcion: Método para personzalizar la salida del tipo 400.
   * @autor: Francisco Javier Cortes Torres.
   * @param: peticionincorrecta Datos de la excepción.
   * @ultimaModificacion: 17/10/2022
   */
  @ExceptionHandler(value = {UnauthorizedException.class})
  public DtoExcepcion unauthorizedException(UnauthorizedException errorSeguridad){
    return new DtoExcepcion(errorSeguridad.getCodigo(),
      errorSeguridad.getMensaje(),
      errorSeguridad.getFolio(),
      errorSeguridad.getDetalles());
  }

  /**
   * <b>notFoundException</b>
   * @descripcion: Método para personzalizar la salida del tipo 400.
   * @autor: Francisco Javier Cortes Torres.
   * @param: peticionincorrecta Datos de la excepción.
   * @ultimaModificacion: 17/10/2022
   */
  @ExceptionHandler(value = {NotFoundException.class})
  public DtoExcepcion notFoundException(NotFoundException recursoNoEncontrado){
    return new DtoExcepcion(recursoNoEncontrado.getCodigo(),
      recursoNoEncontrado.getMensaje(),
      recursoNoEncontrado.getFolio(),
      recursoNoEncontrado.getDetalles());
  }

  /**
   * <b>InternalServerErrorException</b>
   * @descripcion: Método para personzalizar la salida del tipo 400.
   * @autor: Francisco Javier Cortes Torres.
   * @param: peticionincorrecta Datos de la excepción.
   * @ultimaModificacion: 17/10/2022
   */
  @ExceptionHandler(value = {InternalServerErrorException.class})
  public DtoExcepcion internalServerException(InternalServerErrorException errorInterno) {
    return new DtoExcepcion(errorInterno.getCodigo(),
      errorInterno.getMensaje(),
      errorInterno.getFolio(),
      errorInterno.getDetalles());
  }

  /**
   * <b>ErrorInternoException</b>
   * @descripcion: Método para personzalizar la salida de una excepción del tipo 500.
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @param: errorInterno datos de la exepcion
   * @ultimaModificacion: 13/10/22
   */
  @ExceptionHandler(value = {ErrorInternoExepcion.class})
  public DtoHipocoristicoResponse errorInterno(ErrorInternoExepcion errorInterno) {
    /*
    Construccion del metodo de salida
     */
    return new DtoHipocoristicoResponse(
      errorInterno.getCodigo(),
      errorInterno.getNombres(),
      errorInterno.getApellidos(),
      errorInterno.getMensaje());
  }

}
