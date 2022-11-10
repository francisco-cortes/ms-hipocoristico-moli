package com.elektra.hipocoristico.controlador;

import com.elektra.hipocoristico.dto.DtoPeticionHipocoristico;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;


import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class PruebaControladorHipocoristico {

  @Inject
  private ControladorHipocoristico controladorHipocoristico;

  @DisplayName("Prueba de Consumo")
  @Test
  public void pruebaConsumoHipocoristico(){
    String nombres[] = {"LEONARDO", "ISRAEL"};
    String apellidos[] = {"MILLAN", "GARCIA"};
    DtoPeticionHipocoristico peticion = new DtoPeticionHipocoristico();
    peticion.setNombres(nombres);
    peticion.setApellidos(apellidos);
    Response response = controladorHipocoristico.buscarHipocoristico("",peticion);
    String res = String.valueOf(response.getEntity());
    assertTrue(res.contains("codigoRespuesta=200"));
  }

}
