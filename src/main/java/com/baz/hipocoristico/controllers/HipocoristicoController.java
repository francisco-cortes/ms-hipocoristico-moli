package com.baz.hipocoristico.controllers;

import com.baz.hipocoristico.dtos.EstadoResponseDto;
import com.baz.hipocoristico.dtos.HipocoristicoRequestDto;
import com.baz.hipocoristico.dtos.HipocoristicoResponseDto;
import com.baz.hipocoristico.exceptions.ErrorInternoExepcion;
import com.baz.hipocoristico.services.BuscarHipocoristicoService;
import com.baz.hipocoristico.services.MonitoreoService;
import com.baz.hipocoristico.utilis.Constantes;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Stream;

/**
 * <b>HipocoristicoController</b>
 * @descripcion: Controlador principal para el modulo
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 13/10/22
 */
@RestController
@Path("/remesas/hipocoristico")
@Tag(name = "Hipocoristico - Consulta - monitoreo")
public class HipocoristicoController {
  /*
  Inyeccion de instacia del MonitoreoService
   */
  @Inject
  private MonitoreoService monitoreoService;

  /*
  Inyeccion instancia del BuscarHipocoristico
   */
  @Inject
  private BuscarHipocoristicoService buscarHipocoristicoService;

  /**
   * <b>buscarHipocoristico</b>
   * @descripcion: Endpoint POST principal
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @param; peticion contiene los nombres y apellidos de una persona en arreglos de cadenas
   * @ultimaModificacion: 13/10/22
   */

  @POST
  @Path("/buscar-hipocoristico")
  @Operation(operationId = "1",
    summary = "Buscar y reemplazar hipocoristicos")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @APIResponses(value =
    {
      @APIResponse(
        responseCode = Constantes.HTTP_200,
        description = "Respuesta Controlada",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = HipocoristicoResponseDto.class))),
      @APIResponse(
        responseCode = Constantes.HTTP_500,
        description = "Error Interno en la aplicación",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = ErrorInternoExepcion.class))),

    })
  public Response buscarHipocoristico(@RequestBody HipocoristicoRequestDto peticion){
    /*
    obtiene la cantidad de strings del arreglo nombre
     */
    String[] nombres = peticion.getNombres();
    /*
    obtiene la cantida de string del arreglo apellido
     */
    String[] apeliidos = peticion.getApellidos();
    /*
    une los arreglos para iterar despues
     */
    String[] arregloNombreApellidos = Stream.of(peticion.getNombres(), peticion.getApellidos())
      .flatMap(Stream::of).toArray(String[]::new);
    /*
    invoca metodo del servicio principal y crea el objeto con el model hipocoristicoResponseDto
     */
    HipocoristicoResponseDto hipocoristicoResponse =
      buscarHipocoristicoService.iniciaBuscar(nombres,apeliidos,arregloNombreApellidos);

    /*
    retorna el objeto como entidad para el parseo como json
     */
    return Response.ok().entity(hipocoristicoResponse).build();
  }

  /**
   * <b>status</b>
   * @descripcion: Método para validar el estado del microservicio
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 13/10/22
   */
  @GET
  @Path("/estado")
  @Operation(
    operationId = "2",
    summary = "Se realiza el test de disponibilidad al microservicio.")
  @Produces(MediaType.APPLICATION_JSON)
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
