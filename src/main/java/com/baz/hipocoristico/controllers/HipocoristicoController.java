package com.baz.moli.controllers;

import com.baz.moli.dtos.EstadoResponseDto;
import com.baz.moli.dtos.HipocoristicoRequestDto;
import com.baz.moli.dtos.HipocoristicoResponseDto;
import com.baz.moli.exceptions.ErrorInternoExepcion;
import com.baz.moli.services.BuscarHipocoristicoService;
import com.baz.moli.services.MonitoreoService;
import com.baz.moli.utilis.Constantes;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Stream;
/**
        * <b>HipocoristicoController</b>
        * @descripcion: Controlador principal para el modulo
        * @autor: Francisco Javier Cortes Torres, Desarrollador
        * @ultimaModificacion: 03/10/22
       */
@Path("/remesas/hipocoristico")
public class HipocoristicoController {


  /*
  instacia del servicio monitoreo a tra ves de inyeccion
   */
  @Inject
  private MonitoreoService monitoreoService;

  /*
  instancia del serrvicio principal de busqueda a travez de inyeccion
   */
  @Inject
  private BuscarHipocoristicoService buscarHipocoristicoService;

  /**
          * <b>buscarHipocoristico</b>
          * @descripcion: Endpoint POST principal
          * @autor: Francisco Javier Cortes Torres, Desarrollador
          * @param; JsonObjet String arrays
          * @ultimaModificacion: 03/10/22
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
    invoca metodo del servicio principal y crea el objeto con el odel hipocoristicoResponseDto
     */
    HipocoristicoResponseDto hipocoristicoResponse =
      buscarHipocoristicoService.iniciaBuscar(nombres,apeliidos,arregloNombreApellidos);

    /*
    retorna el objeto como entidad para el parceo como json
     */
    return Response.ok().entity(hipocoristicoResponse).build();
  }

  /**
   * <b>status</b>
   * @descripcion: Método para validar el estado del microservicio
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 20/06/22
   */
  @GET
  @Path("/estado")
  @Operation(
    summary = "Metodo de consulta al estado y Uid del microservicio",
    description = "description")
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
