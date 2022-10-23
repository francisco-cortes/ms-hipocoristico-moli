package com.baz.hipocoristico.controllers;

import com.baz.hipocoristico.dtos.EstadoResponseDto;
import com.baz.hipocoristico.services.MonitoreoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * <b>MonitoreoController</b>
 * @descripcion: Controlador principal para el modulo
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 13/10/22
 */
@RestController
@RequestMapping("/remesas/hipocoristico")
@Tag(name = "Monitoreo - hipocoristico")
public class MonitoreoController {
  /*
  Inyeccion de instacia del MonitoreoService
   */
  @Inject
  private MonitoreoService monitoreoService;

  /**
   * <b>status</b>
   * @descripcion: Método para validar el estado del microservicio
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 13/10/22
   */
  @Operation(
    operationId = "2",
    summary = "Se realiza el test de disponibilidad al microservicio.")
  @GetMapping(value ="/status", produces = MediaType.APPLICATION_JSON)
  public Response status(){
    /*
    modelo con con los datos de salida
     */
    EstadoResponseDto estadoResponseDto = monitoreoService.generarUid();
    /*
    retorna el objeto como entidad para el parceo como json
     */
    return Response.ok().entity(estadoResponseDto).build();
  }
}
