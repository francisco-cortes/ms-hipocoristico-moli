package com.elektra.hipocoristico.controlador;

import com.baz.excepciones.DtoExcepcion;
import com.elektra.hipocoristico.dto.DtoPeticionHipocoristico;
import com.elektra.hipocoristico.dto.DtoRespuestaHipocoristico;
import com.elektra.hipocoristico.servicios.ServicioBuscaHipocoristico;
import com.elektra.hipocoristico.util.Constantes;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import org.springframework.http.MediaType;
import javax.ws.rs.core.Response;
/**
 * <b>ControladorHipocorístico</b>
 * @descripcion: Controlador principal para el modulo hipocorístico
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 13/10/22
 */
@RestController
@RequestMapping("/datos/hipocoristico")
@Tag(name = "Hipocorístico - Consulta")
public class ControladorHipocoristico {
  /*
  Inyección instancia del BuscarHipocorístico
   */
  @Inject
  private ServicioBuscaHipocoristico servicioBuscaHipocoristico;
  /**
   * <b>buscarHipocorístico</b>
   * @descripcion: Endpoint POST principal
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @param; peticiòn contiene los nombres y apellidos de una persona en arreglos de cadenas
   * @ultimaModificacion: 13/10/22
   */
  //operación principal
  @Operation(operationId = "1",
    summary = "Buscar y reemplazar hipocorísticos por nombre real")
  @Parameter(name ="token",
    schema = @Schema(type = SchemaType.STRING),
    description = "Token para el inciar al solicitud.",
    example = "022DEE73F8528EA4445B133DDB5B224848B2258B",
    in = ParameterIn.HEADER, required = false)
  @Parameter(name ="uid",
    schema = @Schema(type = SchemaType.STRING),
    description = "Uid para identificacion del service.",
    example = "UID123412341332",
    in = ParameterIn.HEADER, required = true)
  @APIResponses(value =
    {
      @APIResponse(
        responseCode = Constantes.HTTP_200,
        description = "Respuesta Controlada",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = DtoRespuestaHipocoristico.class))),
      @APIResponse(
        responseCode = Constantes.HTTP_400,
        description = "Solicitud incorrecta",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = DtoExcepcion.class))),
      @APIResponse(
        responseCode = Constantes.HTTP_404,
        description = "No Encontrado",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = DtoExcepcion.class))),
      @APIResponse(
        responseCode = Constantes.HTTP_500,
        description = "Error Interno en la aplicación",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = DtoExcepcion.class))),
    })
  @PostMapping(value ="/buscar-hipocoristico",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
  public Response buscarHipocoristico(@RequestHeader(name = "uid", required = Constantes.ES_REQUERIDO) String uid,
                                      @RequestBody DtoPeticionHipocoristico peticion){
    /*
    invoca metodo del servicio principal y crea el objeto con el model hipocoristicoResponseDto
     */
    DtoRespuestaHipocoristico hipocoristicoResponse = servicioBuscaHipocoristico.iniciarBuscar(peticion, uid);
    /*
    retorna el objeto como entidad para él parseo como json
     */
    return Response.ok().entity(hipocoristicoResponse).build();
  }

}
