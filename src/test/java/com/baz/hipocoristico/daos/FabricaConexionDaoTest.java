package com.baz.moli.daos;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class FabricaConexionDaoTest {

  @Inject
  private FabricaConexionDao fabricaConexionDao;

  @DisplayName("Prueba Unitaria sobre FabricaDao")
  @Test
  public void testGetConexion() throws Exception {
    assertNotNull(fabricaConexionDao.getConexion());
  }

}
