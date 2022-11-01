package com.elektra.hipocoristico.propiedades.modelos;

import io.quarkus.runtime.annotations.ConfigGroup;

/**
 * <b>Credenciales</b>
 * @descripcion: Interfaz que contienes las credenciales
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 12/07/22
 */
@ConfigGroup
public interface Credenciales {
  //usuario para base de datos
  String usuario();
  //contraseña para base de datos
  String contrasena();
}
