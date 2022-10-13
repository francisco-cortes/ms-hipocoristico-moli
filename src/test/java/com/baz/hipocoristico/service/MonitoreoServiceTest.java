package com.baz.hipocoristico.service;

import com.baz.hipocoristico.dtos.EstadoResponseDto;
import com.baz.hipocoristico.services.MonitoreoService;
import com.baz.hipocoristico.utilis.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class MonitoreoServiceTest {
  @Inject
  private MonitoreoService monitoreoService;

  @DisplayName("Prueba Unitaria sobre Monitoreo")
  @Test
  public void testUid(){
    EstadoResponseDto estadoResponseDto = monitoreoService.generarUid();
    assertEquals(Constantes.ESTADO_OK, estadoResponseDto.getMensaje());
  }

}
