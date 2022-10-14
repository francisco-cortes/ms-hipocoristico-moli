package com.baz.hipocoristico.exceptions;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <b>ErrorInternoExepcion</b>
 * @descripcion: clase modelo para la respuesta controlada de excepciones
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 13/10/22
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorInternoExepcion extends RuntimeException {
  /*
  codigo operacion
   */
  @Schema(
    example = "500"
  )
  private String codigo;
  /*
  mensaje de operacion
   */
  @Schema(
    example = "Ocurrio un incoveniente al procesar la solicitud."
  )
  private String mensaje;
  /*
  detalles de exepcion
   */
  @Schema(
    example = "Error en la conexion de BD."
  )
  private String detalles;
  /*
  nombres de una person
   */
  @Schema(
    example = "PACO, JAVIER"
  )
  private String[] nombres;

  /*
  apellidos de una persona
   */
  @Schema(
    example = "GARCIA, MARTINEZ"
  )
  private String[] apellidos;


  /*
  constructor
   */
  public ErrorInternoExepcion(String codigo, String mensaje, String detalles, String[] nombres, String[] apellidos) {
    this.codigo = codigo;
    this.mensaje = mensaje;
    this.detalles = detalles;
    this.nombres = nombres.clone();
    this.apellidos = apellidos.clone();
  }

  /*
  getters
   */
  //codigo interno server
  public String getCodigo() {
    return this.codigo;
  }
  //mensaje hipocrsitico
  public String getMensaje() {
    return this.mensaje;
  }
  //detalles exepcion
  public String getDetalles() {
    return this.detalles;
  }
  //nombres de una persona
  public String[] getNombres() {
    return this.nombres.clone();
  }
  //apellidso de una persona
  public String[] getApellidos() {
    return this.apellidos.clone();
  }

}
