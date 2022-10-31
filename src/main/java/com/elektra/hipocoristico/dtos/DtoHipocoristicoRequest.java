package com.elektra.hipocoristico.dtos;

import com.baz.anotaciones.Validacion;
import com.elektra.hipocoristico.utilidades.Constantes;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * <b>DtoHipocoristicoRequest</b>
 * @descripcion: Modelo de datos que da forma y contiene los parametros de entrada para el metodo buscarHipocoristico
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 03/10/2022
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoHipocoristicoRequest {
  /*
  Arreglo de strings para guardar los nombres de una persona
   */
  @Validacion(
    tipoDato = Validacion.tiposDato.VARCHAR,
    requerido = Constantes.ES_REQUERIDO,
    caracteresValidos = Validacion.LETRAS,
    longitudMin = Constantes.LONGITUD_MIN_ENTRADA,
    longitudMax = Constantes.LONGITUD_MAX_ENTRADA
  )
  @Schema(
    example = " [\"PACO\", \"JAVIER\" ]",
    required = Constantes.ES_REQUERIDO,
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
    requerido = Constantes.ES_REQUERIDO,
    caracteresValidos = Validacion.LETRAS,
    longitudMin = Constantes.LONGITUD_MIN_ENTRADA,
    longitudMax = Constantes.LONGITUD_MAX_ENTRADA
  )
  @Schema(
    example = " [\"GARCIA\", \"MTZ\" ]",
    required = Constantes.ES_REQUERIDO,
    minLength = Constantes.LONGITUD_MIN_ENTRADA,
    maxLength = Constantes.LONGITUD_MAX_ENTRADA,
    description = "Arreglos de cadenas, idealmente apellidos de persona, en maysucula"
  )
  private String[] apellidos;
}
