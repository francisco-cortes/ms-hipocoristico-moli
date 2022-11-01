package com.elektra.hipocoristico.propiedades;

import com.elektra.hipocoristico.propiedades.modelos.Configuraciones;
import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;

import java.util.Map;
/**
 * <b>Propiedades</b>
 * @descripcion: interfaz para obtener datos del propiedades.yml
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 04/10/22
 */
@StaticInitSafe
@ConfigMapping(prefix = "conexion")
public interface Propiedades {
  /**
   * <b>conexionesdb</b>
   * @descripcion: apunta a configuracionesdb
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * mapa de configuraciones
   * @ultimaModificacion: 04/10/22
   */
  Map<String , Configuraciones> conexionesdb();
}
