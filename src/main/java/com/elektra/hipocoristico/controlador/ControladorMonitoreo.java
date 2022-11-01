package com.elektra.hipocoristico.controlador;

import com.elektra.hipocoristico.dto.DtoRespuestaEstado;
import com.elektra.hipocoristico.servicios.ServicioMonitoreo;
import com.elektra.hipocoristico.utilidades.Constantes;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import org.springframework.http.MediaType;
import javax.ws.rs.core.Response;

/**
 * <b>ControladorMonitoreo</b>
 * @descripcion: Controlador para el endpoint de estado del servicio
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 13/10/22
 */
@RestController
@RequestMapping("/datos/hipocoristico")
@Tag(name = "Monitoreo - hipocorístico")
public class ControladorMonitoreo {
  /*
  Inyección de instancia del Servicio Monitoreo
   */
  @Inject
  private ServicioMonitoreo servicioMonitoreo;

  /**
   * <b>estado</b>
   * @descripcion: Método para validar el estado del microservicio
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 13/10/22
   */
  @Operation(
    operationId = "2",
    summary = "Se realiza el test de disponibilidad al microservicio.")
  @APIResponses(value =
    {
      @APIResponse(
        responseCode = Constantes.HTTP_200,
        description = "Respuesta Controlada",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = DtoRespuestaEstado.class))),
    })
  @GetMapping(value ="/status", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response estado(){
    /*
    modelo con los datos de salida
     */
    DtoRespuestaEstado dtoRespuestaEstado = servicioMonitoreo.generarUid();
    /*
    retorna el objeto como entidad para él parseo como json
     */
    return Response.ok().entity(dtoRespuestaEstado).build();
  }
}
