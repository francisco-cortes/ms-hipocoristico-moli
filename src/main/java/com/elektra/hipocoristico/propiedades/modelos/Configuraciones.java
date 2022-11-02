package com.elektra.hipocoristico.propiedades.modelos;

import io.quarkus.runtime.annotations.ConfigGroup;

/**
 * <b>Configuraciones</b>
 * @descripcion: Interfaz que contienes las propiedades partículares de la validacion
 * del token a ocupar en las peticiones.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 12/07/22
 */

@ConfigGroup
public interface Configuraciones {
  //grupo de configuraciones credenciales
  Credenciales credenciales();
  //ip base de datos
  String ip();
  //puerto base de datos
  String port();
  //nombre de la base de datos
  String name();
  //primer procedimiento almacenado
  String esquema();
  String paquete();
  String sp();
}
