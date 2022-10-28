package com.elektra.hipocoristico.controladores;

import com.elektra.hipocoristico.dtos.DtoEstadoResponse;
import com.elektra.hipocoristico.servicios.ServicioMonitoreo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import org.springframework.http.MediaType;
import javax.ws.rs.core.Response;

/**
 * <b>ControladorMonitoreo</b>
 * @descripcion: Controlador principal para el modulo
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 13/10/22
 */
@RestController
@RequestMapping("/datos/hipocoristico")
@Tag(name = "Monitoreo - hipocoristico")
public class ControladorMonitoreo {
  /*
  Inyeccion de instacia del ServicioMonitoreo
   */
  @Inject
  private ServicioMonitoreo servicioMonitoreo;

  /**
   * <b>status</b>
   * @descripcion: Método para validar el estado del microservicio
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 13/10/22
   */
  @Operation(
    operationId = "2",
    summary = "Se realiza el test de disponibilidad al microservicio.")
  @GetMapping(value ="/status", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response status(){
    /*
    modelo con con los datos de salida
     */
    DtoEstadoResponse dtoEstadoResponse = servicioMonitoreo.generarUid();
    /*
    retorna el objeto como entidad para el parceo como json
     */
    return Response.ok().entity(dtoEstadoResponse).build();
  }
}
