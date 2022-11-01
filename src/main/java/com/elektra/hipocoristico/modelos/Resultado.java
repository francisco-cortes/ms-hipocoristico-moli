package com.elektra.hipocoristico.modelos;

import com.baz.anotaciones.Codigo;
import com.baz.anotaciones.Mensaje;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <b>Resultado</b>
 * @descripcion: Entidad que obtiene el resultado de cada proceso a
 * validar / ejecutar en el microservicio.
 * @autor: Fredi Daniel Cifuentes Robledo
 * @ultimaModificacion: 13/09/2022
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resultado {
  private String uid="";
  @Codigo
  private String codigo="";
  @Mensaje
  private String mensaje="";
}
