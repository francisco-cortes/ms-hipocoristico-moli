package com.baz.moli.daos;

import com.baz.moli.properties.Properties;
import com.baz.moli.utilis.Constantes;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <b>FabricaDao</b>
 * @descripcion: Administra las conexiones a Base de Datos.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 12/07/22
 */

@ApplicationScoped
public class FabricaConexionDao {
  /**
   * Instancia el objeto para la interface de propiedades.
   **/
  @Inject
  private Properties properties;

  private Connection conexion;

  /**
   * obtenerConexion
   * Descrpcion: creoa le objeto SQL.Connection
   * Autor: Francisco Javier Cortes Torres, Desarrollador
   * params:
   * returns: String
   **/
  public Connection getConexion() throws SQLException {
     /*
    cadena de coenxion otrogada por servicios de base datos
     */
    String cadenaConexion = "jdbc:postgresql://" +
      properties.conexionesdb().get(Constantes.C3REMESASC).ip() + ":" +
      properties.conexionesdb().get(Constantes.C3REMESASC).port()  + "/" +
      properties.conexionesdb().get(Constantes.C3REMESASC).name();
    /*
    construlle y retorna el objeto connection a treves del drive manager
     */
    return DriverManager.getConnection(cadenaConexion,
      properties.conexionesdb().get(Constantes.C3REMESASC).credenciales().usuario(),
      properties.conexionesdb().get(Constantes.C3REMESASC).credenciales().contrasena());
  }

  /**
   * cerrarConexionSinResult
   * Descrpcion: Cierra y termina los procesos resultantes de la consulta a base de datos sin result set ya que hay
   * consulta que no generan y asi se evita un fallo por excepcion
   * Autor: Francisco Javier Cortes Torres, Desarrollador
   * params: Connection, Statement
   * returns: Void
   **/
  public void cerrarConexionSinResult(Connection conexion, Statement declaracionInvocable)
    throws SQLException {

    if (!conexion.isClosed()) {
      conexion.close();
    }

    if (!declaracionInvocable.isClosed()){
      declaracionInvocable.close();
    }
  }

}
