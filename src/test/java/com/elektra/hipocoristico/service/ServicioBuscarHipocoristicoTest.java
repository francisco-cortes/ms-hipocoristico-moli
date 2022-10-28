package com.elektra.hipocoristico.service;

import com.elektra.hipocoristico.dtos.DtoHipocoristicoRequest;
import com.elektra.hipocoristico.dtos.DtoHipocoristicoResponse;
import com.elektra.hipocoristico.servicios.ServicioBuscarHipocoristico;
import com.elektra.hipocoristico.utilidades.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ServicioBuscarHipocoristicoTest {
  private static final String[] APELLIDOS = new String[] {"MILLAN","GARCIAS"};
  @Inject
  private ServicioBuscarHipocoristico servicioBuscarHipocoristico;
  @DisplayName("Prueba Unitaria busqueda 1 hipocoristico")
  @Test
  public void testIniciarBusquedaIdeal(){
    String nombres[] = {"LEONARDO", "PACO"};
    DtoHipocoristicoResponse resp = servicioBuscarHipocoristico.iniciaBuscar(peticion(nombres), "token");
    Assertions.assertEquals(Constantes.UN_HIPOCORISTICO,resp.getMensaje());
  }

  @DisplayName("Prueba Unitaria buqueda 2 o mas hipocoristicos")
  @Test
  public void testIniciarBusquedaDosHipocoristicos(){
    String nombres[] = {"PETE","PACO"};
    DtoHipocoristicoResponse resp = servicioBuscarHipocoristico.iniciaBuscar(peticion(nombres),"token");
    assertEquals(Constantes.DOS_HIPOCORISTICO,resp.getMensaje());
  }

  @DisplayName("Prueba sin hipocorisiticos")
  @Test
  public void testIniciarBusquedaSinHipocoristicos(){
    String nombres[] = {"LEONARDO","ISRAEL"};
    DtoHipocoristicoResponse resp = servicioBuscarHipocoristico.iniciaBuscar(peticion(nombres),"token");
    assertEquals(Constantes.CERO_HIPOCORISTICOS,resp.getMensaje());
  }

  private DtoHipocoristicoRequest peticion(String[] nombres){
    DtoHipocoristicoRequest req = new DtoHipocoristicoRequest();
    req.setNombres(nombres);
    req.setApellidos(APELLIDOS);
    return req;
  }

}
