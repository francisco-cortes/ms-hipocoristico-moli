package com.elektra.hipocoristico.servicio;

import com.elektra.hipocoristico.dto.DtoRespuestaEstado;
import com.elektra.hipocoristico.servicios.ServicioMonitoreo;
import com.elektra.hipocoristico.utilidades.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ServicioMonitoreoTest {
  @Inject
  private ServicioMonitoreo servicioMonitoreo;

  @DisplayName("Prueba Unitaria sobre Monitoreo")
  @Test
  public void testUid(){
    DtoRespuestaEstado dtoRespuestaEstado = servicioMonitoreo.generarUid();
    assertEquals(Constantes.ESTADO_OK, dtoRespuestaEstado.getMensaje());
  }

}
