package com.elektra.hipocoristico.controladores;

import com.elektra.hipocoristico.dtos.DtoHipocoristicoRequest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    DtoHipocoristicoRequest peticion = new DtoHipocoristicoRequest();
    peticion.setNombres(nombres);
    peticion.setApellidos(apellidos);
    Response response = controladorHipocoristico.buscarHipocoristico("","",peticion);
    String res = String.valueOf(response.getEntity());
    assertTrue(res.contains("codigoRespuesta=200"));
  }

}
