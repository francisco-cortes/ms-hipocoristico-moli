package com.elektra.hipocoristico.util;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class PruebaUtilidadCadenas {

  private UtilidadCadenas utilidadCadenas = new UtilidadCadenas();

  @DisplayName("Prueba Unitaria sobre las utilidad de manejo de cadenas y arreglos")
  @Test
  public void probarUnirSubCadena(){
    String entrada [] = {"LEONARDO", "ISRAEL"};
    String esperado = "LEONARDO ISRAEL";
    String resultado = utilidadCadenas.unirSubCadena(entrada);
    assertEquals(resultado,esperado);
  }

  @DisplayName("Prueba Unitaria para saber si hay espacios en una cadena")
  @Test
  public void probarBuscarEspaciosCadena(){
    String entrada = "LEONARDO MILLAN";
    boolean resultado = utilidadCadenas.buscarEspaciosCadena(entrada);
    assertTrue(resultado);
  }
  @DisplayName("Prueba Unitaria separar cadenas")
  @Test
  public void probarSubCadenaSeparada(){
    String entrada = "LEONARDO ISRAEL";
    String esperado[] = {"LEONARDO", "ISRAEL"};
    String[] resultado = utilidadCadenas.subCadenaSeparada(entrada);
    assertEquals(esperado[0],resultado[0]);
  }
}
