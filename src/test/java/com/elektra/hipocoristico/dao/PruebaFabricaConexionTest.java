package com.elektra.hipocoristico.dao;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * <b>PruebaFabricaConexionTest</b>
 * @descripcion: Prueba unitaria sobre fabrica conexión
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 16/11/22
 */
@QuarkusTest
public class PruebaFabricaConexionTest {
  @Inject
  private DaoFabricaConexion daoFabricaConexion;
  /**
   * <b>probarObtenerConexion</b>
   * @descripcion: prueba para saber si hay una conexión a la base
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 16/11/22
   */
  @DisplayName("Prueba Unitaria sobre FabricaDao")
  @Test
  public void probarObtenerConexion() throws Exception {
    assertNotNull(daoFabricaConexion.obtenerConexion());
  }
}
