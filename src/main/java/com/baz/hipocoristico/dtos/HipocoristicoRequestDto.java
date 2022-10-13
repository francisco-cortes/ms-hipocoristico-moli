package com.baz.moli.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * <b>HipocoristicoRequestDto</b>
 * @descripcion: Modelo de datos que da forma y contiene los parametros de entrada para el metodo buscarHipocoristico
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 03/10/2022
 */

@Data
@JsonPropertyOrder({"nombres","apellidos"})
public class HipocoristicoRequestDto {
  /*
  Arreglo de string para guardar los nombres de una persona
   */
  @Schema(
    example = " [\"PACO\", \"JAVIER\" ]",
    description = "Arreglos de cadenas, idealmente nombres de personas, en maysucula"
  )
  private String[] nombres;
  /*
  Arreglo de string para guardar los apellidos de una persona
   */
  @Schema(
    example = " [\"GARCIA\", \"MTZ\" ]",
    description = "Arreglos de cadenas, idealmente apellidos de persona, en maysucula"
  )
  private String[] apellidos;
}
