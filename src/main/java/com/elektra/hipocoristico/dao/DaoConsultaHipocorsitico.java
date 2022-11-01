package com.elektra.hipocoristico.dao;

import com.elektra.hipocoristico.util.UtilidadGenerarExcepcion;
import com.baz.log.LogServicio;
import com.elektra.hipocoristico.util.Constantes;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * <b>ConsultarHipocoristicoDao</b>
 * @descripcion: Clase Dao principal que aloja métodos de consulta de hipocorísticos a base de datos
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 31/05/22
 */

@ApplicationScoped
@Service("ConsultarHipocoristicoDao")
@NoArgsConstructor
public class DaoConsultaHipocorsitico {
  private static final UtilidadGenerarExcepcion UTILIDAD_GENERAR_EXCEPCION = new UtilidadGenerarExcepcion();
  /**
   * Instancia el objeto para conexión a DB a través de DaofabricaConexion
   */
  @Inject
  private DaoFabricaConexion daoFabricaConexion;

  /**
   * <b>ejecutarSp</b>
   * @descripcion: Ejecuta un procedimiento almacenado, obtiene y retorna un String resultado del procedimiento.
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @param: hipocorístico, nombre analizado
   * @param: log, instancia de logger
   * @param: nombres, arreglos de nombres de una persona
   * @param: apellidos, arreglos de apellidos de una persona
   * @ultimaModificacion: 13/10/22
   */
  @Transactional
  public String buscarDiccionario(String hipocoristico, LogServicio log, String uid)
    throws SQLException {
    String nombreClaseMetodo = "DaoConsultaHipocorsitico-buscarDiccionario";
    log.iniciarTiempoMetodo(nombreClaseMetodo, Constantes.NOMBRE_MS);
    /*
    Constantes para índices de parametros en SP
     */
    final int RESPUESTA = 1;
    final int PARAMETRO_PAIS = 2;
    final int PARAMETRO_HIPOCORSITICO = 3;
    //String de respuesta del método
    String respuestaSp;
    // objeto de conexión sql
    Connection conexion = null;
    //objeto callable sql
    CallableStatement declaracionInvocable = null;

    try {
      /*
      Obtiene conexion a base de datos postgres
      */
      conexion = daoFabricaConexion.obtenerConexion();
      /*
      Se declara la query para la ejecucion del sp, ((buscar forma de guardarla en propiedades))
      */
      declaracionInvocable = conexion.prepareCall("{? = call SC_FONET.FNDICCIONARIO(?, ?)}");
      /*
      Salida String nombre solicitado
      */
      declaracionInvocable.registerOutParameter(RESPUESTA, Types.VARCHAR);
      /*
      Datos de entrada, MEXICO por default
      */
      declaracionInvocable.setShort(PARAMETRO_PAIS, Constantes.MEXICO);
      /*
      Datos de entrada, nombre a buscar
      */
      declaracionInvocable.setString(PARAMETRO_HIPOCORSITICO,hipocoristico);
      /*
      Ejecución de sp
      */
      declaracionInvocable.execute();
      /*
      Obtención de salida sp
      */
      respuestaSp = declaracionInvocable.getString(RESPUESTA);
    }
    catch (Exception exception){
      respuestaSp = hipocoristico;
      String mensajeExcepcion = exception.getMessage();
      log.registrarExcepcion(exception,"Error SQL");
      log.registrarMensaje(nombreClaseMetodo,mensajeExcepcion);

      UTILIDAD_GENERAR_EXCEPCION.generarExcepcion(Constantes.HTTP_500,Constantes.CODIGO_ERROR_GENERAL_API,
        mensajeExcepcion,uid);
    }
    finally {
      /*
      cerrar conexión a base postgres
      */
      daoFabricaConexion.cerrarConexion(conexion,declaracionInvocable);
    }
    /*
    retorna salida de sp
     */
    log.terminarTiempoMetodo(nombreClaseMetodo);
    return respuestaSp;
  }

}
