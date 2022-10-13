package com.baz.hipocoristico.service;

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

  private static final String[] NOMBRES = new String[] {"LEONARDO ISRAEL","PACO"};
  private static final String[] APELLIDOS = new String[] {"MILLAN","GARCIA"};

  @Inject
  private BuscarHipocoristicoService buscarHipocoristicoService;

  @DisplayName("Prueba Unitaria busqueda 1 hipocoristico")
  @Test
  public void testIniciarBusquedaIdeal(){
    final String[] ARREGLO_COMPLETO = new String[] {"LEONARDO","PACO","MILLAN","GARCIA"};
    HipocoristicoResponseDto resp = buscarHipocoristicoService.iniciaBuscar(NOMBRES,APELLIDOS,ARREGLO_COMPLETO);
    assertEquals(Constantes.UN_HIPOCORISTICO,resp.getMensaje());
  }

  @DisplayName("Prueba Unitaria buqueda 2 o mas hipocoristicos")
  @Test
  public void testIniciarBusquedaDosHipocoristicos(){
    final String[] ARREGLO_COMPLETO = new String[] {"LEONARDO PETE","PACO","MILLAN","GARCIA"};
    HipocoristicoResponseDto resp = buscarHipocoristicoService.iniciaBuscar(NOMBRES,APELLIDOS,ARREGLO_COMPLETO);
    assertEquals(Constantes.DOS_HIPOCORISTICO,resp.getMensaje());
  }

  @DisplayName("Prueba sin hipocorisiticos")
  @Test
  public void testIniciarBusquedaSinHipocoristicos(){
    final String[] ARREGLO_COMPLETO = new String[] {"LEONARDO","ISRAEL","MILLAN","GARCIA"};
    HipocoristicoResponseDto resp = buscarHipocoristicoService.iniciaBuscar(NOMBRES,APELLIDOS,ARREGLO_COMPLETO);
    assertEquals(Constantes.CERO_HIPOCORISTICOS,resp.getMensaje());
  }

}
