package com.elektra.hipocoristico.daos;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class DaoFabricaConexionTest {

  @Inject
  private DaoFabricaConexion daoFabricaConexion;

  @DisplayName("Prueba Unitaria sobre FabricaDao")
  @Test
  public void testGetConexion() throws Exception {
    assertNotNull(daoFabricaConexion.getConexion());
  }

}
