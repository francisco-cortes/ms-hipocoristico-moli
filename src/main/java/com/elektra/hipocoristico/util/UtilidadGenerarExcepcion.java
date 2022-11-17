package com.elektra.hipocoristico.util;

import org.springframework.stereotype.Service;

import com.baz.excepciones.BadRequestException;
import com.baz.excepciones.InternalServerErrorException;
import com.baz.excepciones.NotFoundException;

/**
 * <b>HipocoristicoUtils</b>
 * @descripcion: Clase principal que contiene métodos genéricos a
 * utilizar en toda la aplicación.
 * @autor: Angel Eduardo Hernández Aguilar.
 * @ultimaModificacion: 26/10/2022
 * */
@Service("UtilidadGenerarExcepcion")
public class UtilidadGenerarExcepcion {
  /**
   * <b>generarExcepcion</b>
   * @descripcion: Método para generar una excepción de un tipo en específico.
   * @autor: Angel Eduardo Hernández Aguilar
   * @param codigoHttp Tipo de estado http al que pertenecerá la excepción.
   * @param codigo Código de error que identifica el proceso que ocasiona la excepción.
   * @param mensaje Mensaje breve y claro que describe el porque de la excepción.
   * @param uid Identificador único del proceso.
   * @ultimaModificacion: 31/08/2022
   */
  public void generarExcepcion(String codigoHttp, String codigo, String mensaje, String uid) {

    switch(codigoHttp){
      case Constantes.CODIGO_HTTP_400:
        throw new BadRequestException(Constantes.CODIGO_HTTP_400, codigo, Constantes.MENSAJE_CODIGO_400,
          uid, Constantes.NOMBRE_MS, mensaje);
      case Constantes.CODIGO_HTTP_404:
        throw new NotFoundException(Constantes.CODIGO_HTTP_404, codigo, Constantes.MENSAJE_CODIGO_404,
          uid, Constantes.NOMBRE_MS, mensaje);
      default:
        throw new InternalServerErrorException(Constantes.CODIGO_HTTP_500, codigo, Constantes.MENSAJE_CODIGO_500,
          uid, Constantes.NOMBRE_MS, mensaje);
    }

  }

}
