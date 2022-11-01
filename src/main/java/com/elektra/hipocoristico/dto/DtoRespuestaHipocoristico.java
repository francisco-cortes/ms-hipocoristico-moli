package com.elektra.hipocoristico.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * <b>NombreResultadoResponse</b>
 * @descripcion: Modelo de datos que obtiene los datos de la peticion buscar hipocoristico
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 31/05/22
 */
@Data
@Getter
@JsonPropertyOrder({"nombres","apellidos","mensaje"})
public class DtoRespuestaHipocoristico {
  /*
  código de respuesta http
   */
  @Schema(
    example = "200",
    description = "Entero numerico, codigo de respuesta del servidor interno")
  private String codigoRespuesta;
  /*
  nombres de una persona
   */
  @Schema(
    example = "[ \"PACO\", \"JAVIER\" ]",
    description = "Arreglos de cadenas, idealmente nombres de personas, en maysucula")
  private String[] nombres;
  /*
  apellidos de una persona
   */
  @Schema(
    example = "[ \"GARCIA\", \"MARTINEZ\" ]",
    description = "Arreglos de cadenas, idealmente apellidos de personas, en maysucula"
  )
  private String[] apellidos;
  /*
  mensaje de opeación
   */
  @Schema(
    example = " \"1 hipocoristico encontrado\" ",
    description = "Cadena, mensajesobre la cantidad de hipocoristicos encontrados"
  )
  private String mensaje;

  /*
  clase constructora
   */
  public DtoRespuestaHipocoristico(String codigoRespuesta, String[] nombres, String[] apellidos,
                                   String mensaje){
    //codigo http
    this.codigoRespuesta = codigoRespuesta;
    //nombres
    this.nombres = nombres.clone();
    // apellidos
    this.apellidos = apellidos.clone();
    // mensaje de error
    this.mensaje = mensaje;
  }

}
