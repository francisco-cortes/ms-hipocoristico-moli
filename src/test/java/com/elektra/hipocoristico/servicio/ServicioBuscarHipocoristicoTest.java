package com.elektra.hipocoristico.servicio;

import com.elektra.hipocoristico.dto.DtoPeticionHipocoristico;
import com.elektra.hipocoristico.dto.DtoRespuestaHipocoristico;
import com.elektra.hipocoristico.servicios.ServicioBuscarHipocoristico;
import com.elektra.hipocoristico.util.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ServicioBuscarHipocoristicoTest {
  public static final String UID = "UID123412341332";
  private static final String[] APELLIDOS = new String[] {"MILLAN","GARCIAS"};
  @Inject
  private ServicioBuscarHipocoristico servicioBuscarHipocoristico;
  @DisplayName("Prueba Unitaria busqueda 1 hipocoristico")
  @Test
  public void testIniciarBusquedaIdeal(){
    String nombres[] = {"LEONARDO", "PACO"};
    DtoRespuestaHipocoristico resp = servicioBuscarHipocoristico.iniciarBuscar(peticion(nombres), UID);
    Assertions.assertEquals(Constantes.UN_HIPOCORISTICO,resp.getMensaje());
  }

  @DisplayName("Prueba Unitaria buqueda 2 o mas hipocoristicos")
  @Test
  public void testIniciarBusquedaDosHipocoristicos(){
    String nombres[] = {"PETE","PACO"};
    DtoRespuestaHipocoristico resp = servicioBuscarHipocoristico.iniciarBuscar(peticion(nombres),UID);
    assertEquals(Constantes.DOS_HIPOCORISTICO,resp.getMensaje());
  }

  @DisplayName("Prueba sin hipocorisiticos")
  @Test
  public void testIniciarBusquedaSinHipocoristicos(){
    String nombres[] = {"LEONARDO","ISRAEL"};
    DtoRespuestaHipocoristico resp = servicioBuscarHipocoristico.iniciarBuscar(peticion(nombres),UID);
    assertEquals(Constantes.CERO_HIPOCORISTICOS,resp.getMensaje());
  }

  private DtoPeticionHipocoristico peticion(String[] nombres){
    DtoPeticionHipocoristico req = new DtoPeticionHipocoristico();
    req.setNombres(nombres);
    req.setApellidos(APELLIDOS);
    return req;
  }

}
