package com.elektra.hipocoristico.dto;

import com.baz.anotaciones.DesCifrarValorAes;
import com.baz.anotaciones.Validacion;
import com.elektra.hipocoristico.util.Constantes;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
/**
 * <b>DtoPeticionHipocoristico</b>
 * @descripcion: Modelo de datos que da forma y contiene los parámetros de entrada para el método buscarHipocoristico
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 03/10/2022
 */
@Getter
@Setter
public class DtoPeticionHipocoristico {
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
  @DesCifrarValorAes
  @Schema(
    example = " [\"PACO\", \"JAVIER\" ]",
    required = Constantes.ES_REQUERIDO,
    minLength = Constantes.LONGITUD_MIN_ENTRADA,
    maxLength = Constantes.LONGITUD_MAX_ENTRADA,
    description = "Arreglos de cadenas, idealmente nombres de personas, en mayúscula"
  )
  private String[] nombres;
  /*
  Arreglo de strings para guardar los apellidos de una persona
   */
  @Validacion(
    tipoDato = Validacion.tiposDato.VARCHAR,
    requerido = Constantes.ES_REQUERIDO,
    caracteresValidos = Validacion.LETRAS + " ",
    longitudMin = Constantes.LONGITUD_MIN_ENTRADA,
    longitudMax = Constantes.LONGITUD_MAX_ENTRADA
  )
  @DesCifrarValorAes
  @Schema(
    example = " [\"GARCIA\", \"MTZ\" ]",
    required = Constantes.ES_REQUERIDO,
    minLength = Constantes.LONGITUD_MIN_ENTRADA,
    maxLength = Constantes.LONGITUD_MAX_ENTRADA,
    description = "Arreglos de cadenas, idealmente apellidos de persona, en mayúscula"
  )
  private String[] apellidos;
}
