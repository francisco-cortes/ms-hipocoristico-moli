package com.baz.hipocoristico.service;

import com.baz.hipocoristico.dtos.HipocoristicoRequestDto;
import com.baz.hipocoristico.dtos.HipocoristicoResponseDto;
import com.baz.hipocoristico.services.BuscarHipocoristicoService;
import com.baz.hipocoristico.utilis.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class BuscarHipocoristicoServiceTest {
  private static final String[] APELLIDOS = new String[] {"MILLAN","GARCIAS"};
  @Inject
  private BuscarHipocoristicoService buscarHipocoristicoService;
  @DisplayName("Prueba Unitaria busqueda 1 hipocoristico")
  @Test
  public void testIniciarBusquedaIdeal(){
    String nombres[] = {"LEONARDO", "PACO"};
    HipocoristicoResponseDto resp = buscarHipocoristicoService.iniciaBuscar(peticion(nombres), "token" );
    assertEquals(Constantes.UN_HIPOCORISTICO,resp.getMensaje());
  }

  @DisplayName("Prueba Unitaria buqueda 2 o mas hipocoristicos")
  @Test
  public void testIniciarBusquedaDosHipocoristicos(){
    String nombres[] = {"PETE","PACO"};
    HipocoristicoResponseDto resp = buscarHipocoristicoService.iniciaBuscar(peticion(nombres),"token");
    assertEquals(Constantes.DOS_HIPOCORISTICO,resp.getMensaje());
  }

  @DisplayName("Prueba sin hipocorisiticos")
  @Test
  public void testIniciarBusquedaSinHipocoristicos(){
    String nombres[] = {"LEONARDO","ISRAEL"};
    HipocoristicoResponseDto resp = buscarHipocoristicoService.iniciaBuscar(peticion(nombres),"token");
    assertEquals(Constantes.CERO_HIPOCORISTICOS,resp.getMensaje());
  }

  private HipocoristicoRequestDto peticion(String[] nombres){
    HipocoristicoRequestDto req = new HipocoristicoRequestDto();
    req.setNombres(nombres);
    req.setApellidos(APELLIDOS);
    return req;
  }

}
