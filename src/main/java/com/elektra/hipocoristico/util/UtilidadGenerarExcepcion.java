package com.elektra.hipocoristico.util;

import org.springframework.stereotype.Service;

import com.baz.excepciones.BadRequestException;
import com.baz.excepciones.InternalServerErrorException;
import com.baz.excepciones.NotFoundException;

/**
 * <b>HipocoristicoUtils</b>
 * @descripcion: Clase principal qure contiene m�todos gen�ricos a
 * utilizar en toda la aplicaci�n.
 * @autor: Angel Eduardo Hern�ndez Aguilar.
 * @ultimaModificacion: 26/10/2022
 * */
@Service("UtilidadGenerarExcepcion")
public class UtilidadGenerarExcepcion {
  /**
   * <b>generarExcepcion</b>
   * @descripcion: M�todo para generar una excepci�n de un tipo en espec�fico.
   * @autor: Angel Eduardo Hern�ndez Aguilar
   * @param codigoHttp Tipo de estado http al que pertenecer� la excepci�n.
   * @param codigo C�digo de error que identifica el proceso que ocasiona la excepci�n.
   * @param mensaje Mensaje breve y claro que describe el porque de la excepci�n.
   * @param uid Identificador �nico del proceso.
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
