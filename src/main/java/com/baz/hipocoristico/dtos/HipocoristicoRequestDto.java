package com.baz.hipocoristico.dtos;

import com.baz.anotaciones.Validacion;
import com.baz.hipocoristico.utilis.Constantes;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * <b>HipocoristicoRequestDto</b>
 * @descripcion: Modelo de datos que da forma y contiene los parametros de entrada para el metodo buscarHipocoristico
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 03/10/2022
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HipocoristicoRequestDto {
  /*
  Arreglo de strings para guardar los nombres de una persona
   */
  @Validacion(
    tipoDato = Validacion.tiposDato.VARCHAR,
    requerido = true,
    caracteresValidos = Validacion.LETRAS,
    longitudMin = Constantes.LONGITUD_MIN_ENTRADA,
    longitudMax = Constantes.LONGITUD_MAX_ENTRADA
  )
  @Schema(
    example = " [\"PACO\", \"JAVIER\" ]",
    required = true,
    minLength = Constantes.LONGITUD_MIN_ENTRADA,
    maxLength = Constantes.LONGITUD_MAX_ENTRADA,
    description = "Arreglos de cadenas, idealmente nombres de personas, en maysucula"
  )
  private String[] nombres;
  /*
  Arreglo de strings para guardar los apellidos de una persona
   */
  @Validacion(
    tipoDato = Validacion.tiposDato.VARCHAR,
    requerido = true,
    caracteresValidos = Validacion.LETRAS,
    longitudMin = Constantes.LONGITUD_MIN_ENTRADA,
    longitudMax = Constantes.LONGITUD_MAX_ENTRADA
  )
  @Schema(
    example = " [\"GARCIA\", \"MTZ\" ]",
    minLength = Constantes.LONGITUD_MIN_ENTRADA,
    maxLength = Constantes.LONGITUD_MAX_ENTRADA,
    description = "Arreglos de cadenas, idealmente apellidos de persona, en maysucula"
  )
  private String[] apellidos;
}
