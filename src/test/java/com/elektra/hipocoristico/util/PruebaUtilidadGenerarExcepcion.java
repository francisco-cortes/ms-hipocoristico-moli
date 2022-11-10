package com.elektra.hipocoristico.util;

import com.baz.excepciones.BadRequestException;
import com.baz.excepciones.InternalServerErrorException;
import com.baz.excepciones.NotFoundException;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.wildfly.common.Assert.assertTrue;

@QuarkusTest
public class PruebaUtilidadGenerarExcepcion {

  private static final UtilidadGenerarExcepcion generaExcepcion = new UtilidadGenerarExcepcion();
  private static final String MENSAJE = "Prueba";
  private static final String UID_PRUEBA = "uid prueba";

  @DisplayName("Prueba Unitaria excepción 400")
  @Test
  public void pruebaGeneraExcepcion(){
    BadRequestException thrown = assertThrows(
      BadRequestException.class,
      () -> generaExcepcion.generarExcepcion(Constantes.CODIGO_HTTP_400,Constantes.MENSAJE_CODIGO_400,
        MENSAJE, UID_PRUEBA),
      "Datos de entrada incorrectos");
    System.out.println(thrown.getMensaje());
    assertEquals(thrown.getMensaje(), Constantes.MENSAJE_CODIGO_400);
  }

  @DisplayName("Prueba Unitaria excepción 404")
  @Test
  public void pruebaGeneraExcepcionNoEncontrado(){
    NotFoundException thrown = assertThrows(
      NotFoundException.class,
      () -> generaExcepcion.generarExcepcion(Constantes.CODIGO_HTTP_404,Constantes.MENSAJE_CODIGO_404,
        MENSAJE, UID_PRUEBA),
      "No encontrado");
    System.out.println(thrown.getMensaje());
    assertEquals(thrown.getMensaje(), Constantes.MENSAJE_CODIGO_404);
  }

  @DisplayName("Prueba Unitaria excepción 500")
  @Test
  public void pruebaGeneraExcepcionInterna(){
    InternalServerErrorException thrown = assertThrows(
      InternalServerErrorException.class,
      () -> generaExcepcion.generarExcepcion(Constantes.CODIGO_HTTP_500,Constantes.MENSAJE_CODIGO_500,
        MENSAJE, UID_PRUEBA),
      "Error Interno");
    System.out.println(thrown.getMensaje());
    assertEquals(thrown.getMensaje(), Constantes.MENSAJE_CODIGO_500);
  }
}
