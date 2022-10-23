package com.baz.hipocoristico.controllers;

import com.baz.excepciones.DtoExcepcion;
import com.baz.hipocoristico.dtos.HipocoristicoRequestDto;
import com.baz.hipocoristico.dtos.HipocoristicoResponseDto;
import com.baz.hipocoristico.services.BuscarHipocoristicoService;
import com.baz.hipocoristico.utilis.Constantes;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import org.springframework.http.MediaType;
import javax.ws.rs.core.Response;
/**
 * <b>HipocoristicoController</b>
 * @descripcion: Controlador principal para el modulo
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 13/10/22
 */
@RestController
@RequestMapping("/remesas/hipocoristico")
@Tag(name = "Hipocoristico - Consulta")
public class HipocoristicoController {


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

  @Operation(operationId = "1",
    summary = "Buscar y reemplazar hipocoristicos")
  /*
  Valores para respuesta controladas
   */
  @Parameter(name ="token",
    schema = @Schema(type = SchemaType.STRING),
    description = "Token para el inciar al solicitud.",
    example = "022DEE73F8528EA4445B133DDB5B224848B2258B",
    in = ParameterIn.HEADER, required = true)
  @Parameter(name ="uid",
    schema = @Schema(type = SchemaType.STRING),
    description = "Uid para identificacion del sservice.",
    example = "UID123412341332",
    in = ParameterIn.HEADER, required = true)
  @APIResponses(value =
    {
      @APIResponse(
        responseCode = Constantes.HTTP_200,
        description = "Respuesta Controlada",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = HipocoristicoResponseDto.class))),
      @APIResponse(
        responseCode = "400",
        description = "Solicitud incorrecta",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = DtoExcepcion.class))),
      @APIResponse(
        responseCode = "401",
        description = "Solicitud incorrecta",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = DtoExcepcion.class))),
      @APIResponse(
        responseCode = "500",
        description = "Error Interno en la aplicación",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = DtoExcepcion.class))),
    })
  @PostMapping(value ="/buscar-hipocoristico",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
  public Response buscarHipocoristico(@RequestBody HipocoristicoRequestDto peticion){

    /*
    invoca metodo del servicio principal y crea el objeto con el model hipocoristicoResponseDto
     */
    HipocoristicoResponseDto hipocoristicoResponse =
      buscarHipocoristicoService.iniciaBuscar(peticion);
    /*
    retorna el objeto como entidad para el parseo como json
     */
    return Response.ok().entity(hipocoristicoResponse).build();
  }

}
