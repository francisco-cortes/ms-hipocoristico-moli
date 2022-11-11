package com.elektra.hipocoristico.servicio;

import com.baz.excepciones.InternalServerErrorException;
import com.elektra.hipocoristico.dto.DtoPeticionHipocoristico;
import com.elektra.hipocoristico.dto.DtoRespuestaHipocoristico;
import com.elektra.hipocoristico.servicios.ServicioBuscaHipocoristico;
import com.elektra.hipocoristico.util.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.wildfly.common.Assert.assertTrue;

@QuarkusTest
public class ServicioBuscaHipocoristicoTest {
  public static final String UID = "UID123412341332";
  private static final String[] APELLIDOS = new String[] {"MILLAN","GARCIAS"};
  @Inject
  private ServicioBuscaHipocoristico servicioBuscaHipocoristico;
  @DisplayName("Prueba Unitaria busqueda 1 hipocoristico")
  @Test
  public void testIniciarBusquedaIdeal(){
    String nombres[] = {"LEONARDO", "PACO"};
    DtoRespuestaHipocoristico resp = servicioBuscaHipocoristico.iniciarBuscar(peticion(nombres), UID);
    Assertions.assertEquals(Constantes.CODIGO_UN_HIPOCORISTICO+Constantes.UN_HIPOCORISTICO,
      resp.getMensaje());
  }

  @DisplayName("Prueba Unitaria buqueda 2 o mas hipocoristicos")
  @Test
  public void testIniciarBusquedaDosHipocoristicos(){
    String nombres[] = {"PETE","PACO"};

    InternalServerErrorException thrown = assertThrows(
      InternalServerErrorException.class,
      () -> servicioBuscaHipocoristico.iniciarBuscar(peticion(nombres),UID),
      "2 hipocoristicos"
                                                      );
    assertTrue(thrown.getDetalles().contains(Constantes.DOS_HIPOCORISTICO));
  }

  @DisplayName("Prueba sin hipocorisiticos")
  @Test
  public void testIniciarBusquedaSinHipocoristicos(){
    String nombres[] = {"LEONARDO","ISRAEL"};
    DtoRespuestaHipocoristico resp = servicioBuscaHipocoristico.iniciarBuscar(peticion(nombres),UID);
    assertEquals(Constantes.CODIGO_SIN_HIPOCORISTICO + Constantes.CERO_HIPOCORISTICOS,
      resp.getMensaje());
  }

  private DtoPeticionHipocoristico peticion(String[] nombres){
    DtoPeticionHipocoristico req = new DtoPeticionHipocoristico();
    req.setNombres(nombres);
    req.setApellidos(APELLIDOS);
    return req;
  }

}
