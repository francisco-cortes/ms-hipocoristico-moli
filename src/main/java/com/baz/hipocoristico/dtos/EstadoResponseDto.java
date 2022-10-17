package com.baz.hipocoristico.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * <b>StatusResponse</b>
 * @descripcion: Entidad que obtiene el mensaje que representa el estado del microservicio.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 12/07/22
 */

@Data
@Setter
@JsonPropertyOrder({"mensaje","folio"})
public class EstadoResponseDto {
  /*
  mensaje
   */
  @Schema(
    example = "OK",
    required = false,
    description = "Mensaje que representa el estado del microservicio."
  )
  public String mensaje;
  /*
  folio generado por remesas
   */
  @Schema(
    example = "UID201910011922",
    required = false,
    description = "Identificador único de la solicitud."
  )
  public String folio;
}
